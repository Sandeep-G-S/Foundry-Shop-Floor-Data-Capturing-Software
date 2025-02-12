using Microsoft.EntityFrameworkCore;
using WebApplication1.Models.Entities;

namespace WebApplication1.Data
{
    public class ApplicationDbContext : DbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options) 
        {  }
        public DbSet<LoginForm> LoginForms { get; set; }
        public DbSet<GetDetail> GetDetails { get; set; }
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.Entity<GetDetail>().HasNoKey();
            modelBuilder.Entity<Test>().HasNoKey();
        }
        public DbSet<Test> tests { get; set; }
        public DbSet<User> Users { get; set; }

        public DbSet<MouldingData> MouldingData { get; set; }
    }
}
