using Microsoft.AspNetCore.Mvc;
using WebApplication1.Data;
using WebApplication1.Models.Entities;

namespace WebApplication1.Controllers
{
    [ApiController]
    [Route("api/Mouldingdata")]
    public class MouldingDataController : ControllerBase
    {
        private readonly ApplicationDbContext _context;

        public MouldingDataController(ApplicationDbContext context)
        {
            _context = context;
        }

        [HttpPost("PostMouldingData")]
        public async Task<IActionResult> PostMouldingData([FromBody] MouldingData data)
        {
            if ( !ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.MouldingData.Add(data);
            await _context.SaveChangesAsync();

            return Ok();
        }
    }

}
