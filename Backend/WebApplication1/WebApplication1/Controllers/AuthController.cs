using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using WebApplication1.Services;
using WebApplication1.Data;
using WebApplication1.Models.Entities;

namespace WebApplication1.Controllers
{
    [ApiController]
    [Route("api/auth")]
    public class AuthController : ControllerBase
    {
        private readonly ITokenService _tokenService;
        private readonly ApplicationDbContext _context;

        public AuthController(ITokenService tokenService, ApplicationDbContext context)
        {
            _tokenService = tokenService;
            _context = context;
        }

        [HttpPost("login")]
        public async Task<IActionResult> Login([FromBody] LoginRequest loginRequest)
        {
            var user = await _context.Users.SingleOrDefaultAsync(u => u.Email == loginRequest.Email);

            if (user != null && loginRequest.Password == user.Password)
            {
                var accessToken = _tokenService.GenerateAccessToken(user.Email);
                var refreshToken = _tokenService.GenerateRefreshToken();

                user.RefreshToken = refreshToken;
                user.RefreshTokenExpiryTime = DateTime.UtcNow.AddDays(7); // Refresh token valid for 7 days

                await _context.SaveChangesAsync();

                return Ok(new { AccessToken = accessToken, RefreshToken = refreshToken });
            }

            return Unauthorized();
        }

        [HttpPost("refresh")]
        public async Task<IActionResult> Refresh([FromBody] TokenRequest tokenRequest)
        {
            var principal = _tokenService.GetPrincipalFromExpiredToken(tokenRequest.AccessToken);
            var email = principal.Identity.Name;

            var user = await _context.Users.SingleOrDefaultAsync(u => u.Email == email);

            if (user == null || user.RefreshToken != tokenRequest.RefreshToken || user.RefreshTokenExpiryTime <= DateTime.UtcNow)
            {
                return Unauthorized();
            }

            var newAccessToken = _tokenService.GenerateAccessToken(user.Email);
            var newRefreshToken = _tokenService.GenerateRefreshToken();

            user.RefreshToken = newRefreshToken;
            user.RefreshTokenExpiryTime = DateTime.UtcNow.AddDays(7);

            await _context.SaveChangesAsync();

            return Ok(new { AccessToken = newAccessToken, RefreshToken = newRefreshToken });
        }
    }

    public class LoginRequest
    {
        public string Email { get; set; }
        public string Password { get; set; }
    }

    public class TokenRequest
    {
        public string AccessToken { get; set; }
        public string RefreshToken { get; set; }
    }
}
