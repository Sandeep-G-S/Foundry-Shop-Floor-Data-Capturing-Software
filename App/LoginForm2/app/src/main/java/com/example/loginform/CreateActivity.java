package com.example.loginform;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivity extends AppCompatActivity {
    private EditText editTextDate, editTextTime, editTextDateCode, editTextSupervisor,
            editTextOperator, editTextShift, editTextMouldingMachine, editTextFoundryCode, editTextNoOfCavities,
            editTextPartNumberStatus, editTextCavityNumberStatus, editTextDateCodeStatus, editTextMouldBreakageStatusRam,
            editTextMouldBreakageStatusSwing, editTextMouldsMadePlan, editTextMouldsMadeActual, editTextMouldsPouredPlan,
            editTextMouldsPouredActual, editTextLooseSand, editTextCoreSeating, editTextSprayNozzleCondition,
            editTextVisualCastingInspection, editTextMouldCrush, editTextFilterPlacing, editTextChillPlacing,
            editTextAirBlowOff, editTextMouldHardnessRam, editTextMouldHardnessSwing, editTextPPChangeDelay,
            editTextProductionDelay, editTextMouldMaintenanceDelay, editTextLaddleDelay, editTextMeltingDelay,
            editTextStopperRodIssues, editTextTundishChange, editTextMeltMaintenanceDelay, editTextPowerCut,
            editTextNptTrials, editTextImprovementTrial, editTextStartUpDelay, editTextPatternRelatedProblem,
            editTextChamberHeight, editTextChamberGap, editTextNoOfMouldsTakeOut, editTextMouldBrokenQuantity;

    private Button submitButton;
    private ApiService apiService;
    private SessionManager sessionManager;

    private Spinner spinnerOptions;
    private String Line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        spinnerOptions = findViewById(R.id.spinnerOptions);
        editTextDate = findViewById(R.id.editTextDate);
        editTextTime = findViewById(R.id.editTextTime);
        editTextDateCode = findViewById(R.id.editTextDateCode);
        editTextSupervisor = findViewById(R.id.editTextSupervisor);
        editTextOperator = findViewById(R.id.editTextOperator);
        editTextShift = findViewById(R.id.editTextShift);
        editTextMouldingMachine = findViewById(R.id.editTextMouldingMachine);
        editTextFoundryCode = findViewById(R.id.editTextFoundryCode);
        editTextNoOfCavities = findViewById(R.id.editTextNoOfCavities);
        editTextPartNumberStatus = findViewById(R.id.editTextPartNumberStatus);
        editTextCavityNumberStatus = findViewById(R.id.editTextCavityNumberStatus);
        editTextDateCodeStatus = findViewById(R.id.editTextDateCodeStatus);
        editTextMouldBreakageStatusRam = findViewById(R.id.editTextMouldBreakageStatusRam);
        editTextMouldBreakageStatusSwing = findViewById(R.id.editTextMouldBreakageStatusSwing);
        editTextMouldsMadePlan = findViewById(R.id.editTextMouldsMadePlan);
        editTextMouldsMadeActual = findViewById(R.id.editTextMouldsMadeActual);
        editTextMouldsPouredPlan = findViewById(R.id.editTextMouldsPouredPlan);
        editTextMouldsPouredActual = findViewById(R.id.editTextMouldsPouredActual);
        editTextLooseSand = findViewById(R.id.editTextLooseSand);
        editTextCoreSeating = findViewById(R.id.editTextCoreSeating);
        editTextSprayNozzleCondition = findViewById(R.id.editTextSprayNozzleCondition);
        editTextVisualCastingInspection = findViewById(R.id.editTextVisualCastingInspection);
        editTextMouldCrush = findViewById(R.id.editTextMouldCrush);
        editTextFilterPlacing = findViewById(R.id.editTextFilterPlacing);
        editTextChillPlacing = findViewById(R.id.editTextChillPlacing);
        editTextAirBlowOff = findViewById(R.id.editTextAirBlowOff);
        editTextMouldHardnessRam = findViewById(R.id.editTextMouldHardnessRam);
        editTextMouldHardnessSwing = findViewById(R.id.editTextMouldHardnessSwing);
        editTextPPChangeDelay = findViewById(R.id.editTextPPChangeDelay);
        editTextProductionDelay = findViewById(R.id.editTextProductionDelay);
        editTextMouldMaintenanceDelay = findViewById(R.id.editTextMouldMaintenanceDelay);
        editTextLaddleDelay = findViewById(R.id.editTextLaddleDelay);
        editTextMeltingDelay = findViewById(R.id.editTextMeltingDelay);
        editTextStopperRodIssues = findViewById(R.id.editTextStopperRodIssues);
        editTextTundishChange = findViewById(R.id.editTextTundishChange);
        editTextMeltMaintenanceDelay = findViewById(R.id.editTextMeltMaintenanceDelay);
        editTextPowerCut = findViewById(R.id.editTextPowerCut);
        editTextNptTrials = findViewById(R.id.editTextNptTrials);
        editTextImprovementTrial = findViewById(R.id.editTextImprovementTrial);
        editTextStartUpDelay = findViewById(R.id.editTextStartUpDelay);
        editTextPatternRelatedProblem = findViewById(R.id.editTextPatternRelatedProblem);
        editTextChamberHeight = findViewById(R.id.editTextChamberHeight);
        editTextChamberGap = findViewById(R.id.editTextChamberGap);
        editTextNoOfMouldsTakeOut = findViewById(R.id.editTextNoOfMouldsTakeOut);
        editTextMouldBrokenQuantity = findViewById(R.id.editTextMouldBrokenQuantity);
        submitButton = findViewById(R.id.submitButton);

        // Initialize SessionManager
        sessionManager = new SessionManager(this);

        // Initialize ApiService using RetrofitClient
        apiService = RetrofitClient.getApiService();

        // Set onClick listener for the submit button
        submitButton.setOnClickListener(v -> submitData());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOptions.setAdapter(adapter);

        spinnerOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Line = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Optional: handle no selection case if needed
            }
        });

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });
    }

    private void submitData() {
        MouldingData dataModel = new MouldingData();
        dataModel.setMeasurementDate(editTextDate.getText().toString());
        dataModel.setMeasurementTime(editTextTime.getText().toString());
        dataModel.setDateCode(editTextDateCode.getText().toString());
        dataModel.setSupervisor(editTextSupervisor.getText().toString());
        dataModel.setOperator(editTextOperator.getText().toString());
        dataModel.setShift(editTextShift.getText().toString());
        dataModel.setMouldingMachine(editTextMouldingMachine.getText().toString());
        dataModel.setFoundryCode(editTextFoundryCode.getText().toString());
        dataModel.setNoOfCavities(editTextNoOfCavities.getText().toString());
        dataModel.setPartNumberStatus(editTextPartNumberStatus.getText().toString());
        dataModel.setCavityNumberStatus(editTextCavityNumberStatus.getText().toString());
        dataModel.setDateCodeStatus(editTextDateCodeStatus.getText().toString());
        dataModel.setMouldBreakageStatusRam(editTextMouldBreakageStatusRam.getText().toString());
        dataModel.setMouldBreakageStatusSwing(editTextMouldBreakageStatusSwing.getText().toString());
        dataModel.setMouldsMadePlan(editTextMouldsMadePlan.getText().toString());
        dataModel.setMouldsMadeActual(editTextMouldsMadeActual.getText().toString());
        dataModel.setMouldsPouredPlan(editTextMouldsPouredPlan.getText().toString());
        dataModel.setMouldsPouredActual(editTextMouldsPouredActual.getText().toString());
        dataModel.setLooseSand(editTextLooseSand.getText().toString());
        dataModel.setCoreSeating(editTextCoreSeating.getText().toString());
        dataModel.setSprayNozzleCondition(editTextSprayNozzleCondition.getText().toString());
        dataModel.setVisualCastingInspection(editTextVisualCastingInspection.getText().toString());
        dataModel.setMouldCrush(editTextMouldCrush.getText().toString());
        dataModel.setFilterPlacing(editTextFilterPlacing.getText().toString());
        dataModel.setChillPlacing(editTextChillPlacing.getText().toString());
        dataModel.setAirBlowOff(editTextAirBlowOff.getText().toString());
        dataModel.setMouldHardnessRam(editTextMouldHardnessRam.getText().toString());
        dataModel.setMouldHardnessSwing(editTextMouldHardnessSwing.getText().toString());
        dataModel.setPpChangeDelay(editTextPPChangeDelay.getText().toString());
        dataModel.setProductionDelay(editTextProductionDelay.getText().toString());
        dataModel.setMouldMaintenanceDelay(editTextMouldMaintenanceDelay.getText().toString());
        dataModel.setLaddleDelay(editTextLaddleDelay.getText().toString());
        dataModel.setMeltingDelay(editTextMeltingDelay.getText().toString());
        dataModel.setStopperRodIssues(editTextStopperRodIssues.getText().toString());
        dataModel.setTundishChange(editTextTundishChange.getText().toString());
        dataModel.setMeltMaintenanceDelay(editTextMeltMaintenanceDelay.getText().toString());
        dataModel.setPowerCut(editTextPowerCut.getText().toString());
        dataModel.setNptTrials(editTextNptTrials.getText().toString());
        dataModel.setImprovementTrial(editTextImprovementTrial.getText().toString());
        dataModel.setStartUpDelay(editTextStartUpDelay.getText().toString());
        dataModel.setPatternRelatedProblem(editTextPatternRelatedProblem.getText().toString());
        dataModel.setChamberHeight(editTextChamberHeight.getText().toString());
        dataModel.setChamberGap(editTextChamberGap.getText().toString());
        dataModel.setNoOfMouldsTakeOut(editTextNoOfMouldsTakeOut.getText().toString());
        dataModel.setMouldBrokenQuantity(editTextMouldBrokenQuantity.getText().toString());

        dataModel.setLine(Line);

        String token = sessionManager.getToken();

        Call<Void> call = apiService.postMouldingData(dataModel);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CreateActivity.this, "Data submitted successfully", Toast.LENGTH_SHORT).show();
                    // Reset form or navigate back as needed
                } else {
                    Log.e("SubmitData", "Submission failed: " + response.code() + " " + response.message());

                    try {
                        Log.e("SubmitData", "Error body: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CreateActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        editTextDate.setText(selectedDate);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String selectedTime = hourOfDay + ":" + minute;
                        editTextTime.setText(selectedTime);
                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }
}
