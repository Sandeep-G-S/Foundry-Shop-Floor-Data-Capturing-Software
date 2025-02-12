using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using WebApplication1.Controllers;
using WebApplication1.Data;
using WebApplication1.Models.Entities;
using WebApplication1.Models;


namespace WebApplication1.Controllers
{
    // localhost:xxxx/api/loginforms
    [Route("api/[controller]")]
    [ApiController]
    public class LoginFormsController : ControllerBase
    {
        private readonly ApplicationDbContext dbContext;
        private readonly IConfiguration configuration;

        public LoginFormsController(ApplicationDbContext dbContext, IConfiguration configuration)
        {
            this.dbContext = dbContext;
            this.configuration = configuration;
        }
        [HttpGet]
        public IActionResult GetAllLoginForms()
        {
            var allLoginForms = dbContext.LoginForms.ToList();
            return Ok(allLoginForms);
        }


        [HttpPost]
        public IActionResult AddLoginFormData(AddLoginFormDto addLoginFormDto)
        {
            var loginEntity = new LoginForm()
            {
                Email = addLoginFormDto.Email,
                Password = addLoginFormDto.Password,
            };
            dbContext.Add(loginEntity);
            dbContext.SaveChanges();
            return Ok(loginEntity);
        }



        [HttpPost]
        [Route("Login")]
        public IActionResult AddLoginForm(AddLoginFormDto addLoginFormDto)
        {
            var loginformEntity = dbContext.LoginForms.FirstOrDefault(x => x.Email == addLoginFormDto.Email && x.Password == addLoginFormDto.Password);


            if (loginformEntity != null)
            {
                var claims = new[]
                {
                    new Claim(JwtRegisteredClaimNames.Sub, configuration["Jwt:Subject"]),
                    new Claim(JwtRegisteredClaimNames.Jti, Guid.NewGuid().ToString()),
                    new Claim("Email", loginformEntity.Email.ToString()),
                    new Claim("Password",loginformEntity.Password.ToString())
                };

                var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(configuration["Jwt:Key"]));
                var signIn = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);
                var token = new JwtSecurityToken(
                    configuration["Jwt:Issuer"],
                    configuration["Jwt:Audience"],
                    claims,
                    expires: DateTime.UtcNow.AddMinutes(60),
                    signingCredentials: signIn
                    );
                string tokenValue = new JwtSecurityTokenHandler().WriteToken(token);

                return Ok(new { Token = tokenValue, User = loginformEntity });
            }
            //return Ok(loginformEntity); 
            return NoContent();
        }

        [Authorize]
        [HttpGet]
        [Route("{id:guid}")]
        public IActionResult GetLoginFormById(Guid id)
        {
            var loginform = dbContext.LoginForms.Find(id);

            if (loginform == null)  
            {
                return NotFound();
            }
            return Ok(loginform);
        }


    }
}