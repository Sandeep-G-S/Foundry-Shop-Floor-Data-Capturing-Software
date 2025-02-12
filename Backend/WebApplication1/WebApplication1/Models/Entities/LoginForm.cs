namespace WebApplication1.Models.Entities
{
    public class LoginForm  
    {   
        public Guid Id { get; set; }
        public required string Email {  get; set; }
        public required string Password { get; set; }   
    }
}   
