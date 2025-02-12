using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace WebApplication1.Migrations
{
    /// <inheritdoc />
    public partial class initial : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "GetDetails",
                columns: table => new
                {
                    Email = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    Password = table.Column<string>(type: "nvarchar(max)", nullable: false)
                },
                constraints: table =>
                {
                });

            migrationBuilder.CreateTable(
                name: "LoginForms",
                columns: table => new
                {
                    Id = table.Column<Guid>(type: "uniqueidentifier", nullable: false),
                    Email = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    Password = table.Column<string>(type: "nvarchar(max)", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_LoginForms", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "MouldingData",
                columns: table => new
                {
                    Id = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    Line = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    MeasurementDate = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    MeasurementTime = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    DateCode = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    Supervisor = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    Operator = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    Shift = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    MouldingMachine = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    FoundryCode = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    NoOfCavities = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    PartNumberStatus = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    CavityNumberStatus = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    DateCodeStatus = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    MouldBreakageStatusRam = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    MouldBreakageStatusSwing = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    MouldsMadePlan = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    MouldsMadeActual = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    MouldsPouredPlan = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    MouldsPouredActual = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    LooseSand = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    CoreSeating = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    SprayNozzleCondition = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    VisualCastingInspection = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    MouldCrush = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    FilterPlacing = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    ChillPlacing = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    AirBlowOff = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    MouldHardnessRam = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    MouldHardnessSwing = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    PPChangeDelay = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    ProductionDelay = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    MoldMaintDelay = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    LaddleDelayMetalDistribution = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    MeltingDelay = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    StopperRodPlungerRodIssues = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    TundishChangeNozzleChange = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    MeltMaintDelay = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    PowerCut = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    NPTTrials = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    ImprovementTrial = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    StartUpDelay = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    PatternRelatedProblem = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    ChamberHeight = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    ChamberGapRamSwing = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    NumberOfMouldsTakeOutDueToLeak = table.Column<string>(type: "nvarchar(max)", nullable: true),
                    MouldBrokenQuantity = table.Column<string>(type: "nvarchar(max)", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_MouldingData", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "tests",
                columns: table => new
                {
                    Email = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    Password = table.Column<string>(type: "nvarchar(max)", nullable: false)
                },
                constraints: table =>
                {
                });

            migrationBuilder.CreateTable(
                name: "Users",
                columns: table => new
                {
                    Id = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    Email = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    Password = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    RefreshToken = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    RefreshTokenExpiryTime = table.Column<DateTime>(type: "datetime2", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Users", x => x.Id);
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "GetDetails");

            migrationBuilder.DropTable(
                name: "LoginForms");

            migrationBuilder.DropTable(
                name: "MouldingData");

            migrationBuilder.DropTable(
                name: "tests");

            migrationBuilder.DropTable(
                name: "Users");
        }
    }
}
