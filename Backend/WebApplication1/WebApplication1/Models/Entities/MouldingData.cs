using System.ComponentModel.DataAnnotations;

namespace WebApplication1.Models.Entities
{
    public class MouldingData
    {
        public int Id { get; set; }
        public string?  Line { get; set; }
        public string? MeasurementDate { get; set; }
        public string? MeasurementTime { get; set; }
        public string? DateCode { get; set; }
        public string? Supervisor { get; set; }
        public string? Operator { get; set; }
        public string? Shift { get; set; }
        public string? MouldingMachine { get; set; }
        public string? FoundryCode { get; set; }
        public string? NoOfCavities { get; set; }
        public string? PartNumberStatus { get; set; }
        public string? CavityNumberStatus { get; set; }
        public string? DateCodeStatus { get; set; }
        public string? MouldBreakageStatusRam { get; set; }
        public string? MouldBreakageStatusSwing { get; set; }
        public string? MouldsMadePlan { get; set; }
        public string? MouldsMadeActual { get; set; }
        public string? MouldsPouredPlan { get; set; }
        public string? MouldsPouredActual { get; set; }
        public string? LooseSand { get; set; }
        public string? CoreSeating { get; set; }
        public string? SprayNozzleCondition { get; set; }
        public string? VisualCastingInspection { get; set; }
        public string? MouldCrush { get; set; }
        public string? FilterPlacing { get; set; }
        public string? ChillPlacing { get; set; }
        public string? AirBlowOff { get; set; }
        public string? MouldHardnessRam { get; set; }
        public string? MouldHardnessSwing { get; set; }
        public string? PPChangeDelay { get; set; }
        public string? ProductionDelay { get; set; }
        public string? MoldMaintDelay { get; set; }
        public string? LaddleDelayMetalDistribution { get; set; }
        public string? MeltingDelay { get; set; }
        public string? StopperRodPlungerRodIssues { get; set; }
        public string? TundishChangeNozzleChange { get; set; }
        public string? MeltMaintDelay { get; set; }
        public string? PowerCut { get; set; }
        public string? NPTTrials { get; set; }
        public string? ImprovementTrial { get; set; }
        public string? StartUpDelay { get; set; }
        public string? PatternRelatedProblem { get; set; }
        public string? ChamberHeight { get; set; }
        public string? ChamberGapRamSwing { get; set; }
        public string? NumberOfMouldsTakeOutDueToLeak { get; set; }
        public string? MouldBrokenQuantity { get; set; }
    }

}
