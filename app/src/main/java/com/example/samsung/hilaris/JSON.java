package com.example.samsung.hilaris;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class JSON extends JSONObject implements Serializable {

    check converter = new check();
    transient JSONObject json;

    double AgeMatched;
    double AgeMathcedPer;
    double AgilityMovementUBLeft;
    double AgilityMovementUBRight;
    double AgilityMovementULLeft;
    double AgilityMovementULRight;
    double AgilityRatioUBLeft;
    double AgilityRatioUBRight;
    double AgilityRatioULLeft;
    double AgilityRatioULRight;
    double AgilityReactionUBLeft;
    double AgilityReactionUBRight;
    double AgilityReactionULLeft;
    double AgilityReactionULRight;
    double BMI;
    double BPRecvDiastolicLeft;
    double BPRecvDiastolicRight;
    double BPRecvMeanLeft;
    double BPRecvMeanRight;
    double BPRecvPulseRateLeft;
    double BPRecvPulseRateRight;
    double BPRecvSystolicLeft;
    double BPRecvSystolicRight;
    double BPRestDiastolicLeft;
    double BPRestDiastolicRight;
    double BPRestMeanLeft;
    double BPRestMeanRight;
    double BPRestPulseRateLeft;
    double BPRestPulseRateRight;
    double BPRestSystolicLeft;
    double BPRestSystolicRight;
    double BPStimDiastolicLeft;
    double BPStimDiastolicRight;
    double BPStimMeanLeft;
    double BPStimMeanRight;
    double BPStimPulseRateLeft;
    double BPStimPulseRateRight;
    double BPStimSystolicLeft;
    double BPStimSystolicRight;
    double BalancePer;
    String Birthdate;
    double BmiPer;
    double BodyAge;
    double BodyFatPer;
    double BodyFatPercent;
    double BodyMineralPer;
    double BodyProtein;
    double BodySkeletalPer;
    double BodyWaterPer;
    double Calorie;
    double CardioAge;
    double CardioWattLoad;
    double Fat;
    double FitnessAge;
    double FitnessWatt;
    double FlexLeft;
    double FlexLeftPer;
    double FlexRight;
    double FlexRightPer;
    String Gender;
    double HRRecv;
    double HRRest;
    double HRStim;
    double HandEnduranceLeft;
    double HandEnduranceLeftPer;
    double HandEnduranceRight;
    double HandEnduranceRightPer;
    double HandMaxKgfLeft;
    double HandMaxKgfRight;
    double HandStrengthLeft;
    double HandStrengthLeftPer;
    double HandStrengthRight;
    double HandStrengthRightPer;
    double Height;
    double HeightPer;
    double doubleraCellularWater;
    double LeftArm_BodyFatPercent;
    double LeftArm_ExtraCellularWater;
    double LeftArm_doubleraCellularWater;
    double LeftArm_Mineral;
    double LeftArm_Protein;
    double LeftArm_SkeletalMuscle;
    double LeftLeg_BodyFatPercent;
    double LeftLeg_ExtraCellularWater;
    double LeftLeg_doubleraCellularWater;
    double LeftLeg_Mineral;
    double LeftLeg_Protein;
    double LeftLeg_SkeletalMuscle;
    double LegEnduranceLeft;
    double LegEnduranceLeftPer;
    double LegEnduranceRight;
    double LegEnduranceRightPer;
    double LegStrengthLeft;
    double LegStrengthLeftPer;
    double LegStrengthRight;
    double LegStrengthRightPer;
    String MedifitTestGUID;
    double Mineral;
    double OpenCenterX;
    double OpenCenterY;
    double OpenEnvelopArea;
    String PhoneNumber;
    double Power;
    double PowerPer;
    double Protein;
    double ReactionLeftPer;
    double ReactionRightPer;
    double ResponseLeftPer;
    double ResponseRightPer;
    double RightArm_BodyFatPercent;
    double RightArm_ExtraCellularWater;
    double RightArm_doubleraCellularWater;
    double RightArm_Mineral;
    double RightArm_Protein;
    double RightArm_SkeletalMuscle;
    double RightLeg_BodyFatPercent;
    double RightLeg_ExtraCellularWater;
    double RightLeg_doubleraCellularWater;
    double RightLeg_Mineral;
    double RightLeg_Protein;
    double RightLeg_SkeletalMuscle;
    double RombergQuotient;
    double SeatPosition;
    double SkeletalMuscle;
    double StandingLeft;
    double StandingRight;
    double TargetHR;
    String TestDate;
    String TestID;
    String TestType;
    double TravelDistance;
    double Trunk_BodyFatPercent;
    double Trunk_ExtraCellularWater;
    double Trunk_DoubleraCellularWater;
    double Trunk_Mineral;
    double Trunk_Protein;
    double Trunk_SkeletalMuscle;
    String UserAge;
    String UserID;
    String UserName;
    double Vo2Max;
    double Vo2maxPer;
    double Waist;
    double WaistPer;
    double Weight;
    double WeightLeftBottomPer;
    double WeightLeftTopPer;
    double WeightPer;
    double WeightRightBottomPer;
    double WeightRightTopPer;
    double YongNormalPer;
    double YoungNormal;
    int Extension;
    int Flexion;
    int BendingLeft;
    int BendingRight;
    int RotationLeft;
    int RotationRight;

    int PowerPeak;
    int PowerMean;
    int FatigueIndex;
    int FatigueSlope;

    public JSON(JSONObject JsonObject) throws JSONException {
        json = JsonObject;
    }

    public double getAgeMatched()  throws JSONException {
        return Math.round(json.getDouble("AgeMatched")*100d)/100d;
    }

    public double getAgeMathcedPer() throws JSONException {
        return Math.round(json.getDouble("AgeMathcedPer")*100d)/100d;
    }

    public double getAgilityMovementUBLeft() throws JSONException {
        return Math.round(json.getDouble("AgilityMovementUBLeft")*100d)/100d;
    }

    public double getAgilityMovementUBRight() throws JSONException {
        return Math.round(json.getDouble("AgilityMovementUBRight")*100d)/100d;
    }
    public double getAgilityMovementULLeft() throws JSONException {
        return Math.round(json.getDouble("AgilityMovementULLeft")*100d)/100d;
    }

    public double getAgilityMovementULRight() throws JSONException {
        return Math.round(json.getDouble("AgilityMovementULRight")*100d)/100d;
    }

    public double getAgilityRatioUBLeft() throws JSONException {
        return Math.round(json.getDouble("AgilityRatioUBLeft")*100d)/100d;
    }

    public double getAgilityRatioUBRight() throws JSONException {
        return Math.round(json.getDouble("AgilityRatioUBRight")*100d)/100d;
    }

    public double getAgilityRatioULLeft() throws JSONException {
        return Math.round(json.getDouble("AgilityRatioULLeft")*100d)/100d;
    }

    public double getAgilityRatioULRight() throws JSONException {
        return Math.round(json.getDouble("AgilityRatioULRight")*100d)/100d;
    }

    public double getAgilityReactionUBLeft() throws JSONException {
        return Math.round(json.getDouble("AgilityReactionUBLeft")*100d)/100d;
    }

    public double getAgilityReactionUBRight() throws JSONException {
        return Math.round(json.getDouble("AgilityReactionUBRight")*100d)/100d;
    }

    public double getAgilityReactionULLeft() throws JSONException {
        return Math.round(json.getDouble("AgilityReactionULLeft")*100d)/100d;
    }

    public double getAgilityReactionULRight() throws JSONException {
        return Math.round(json.getDouble("AgilityReactionULRight")*100d)/100d;
    }

    public double getBMI() throws JSONException {
        return Math.round(json.getDouble("BMI")*100d)/100d;
    }

    public double getBPRecvDiastolicLeft() throws JSONException {
        return Math.round(json.getDouble("BPRecvDiastolicLeft")*100d)/100d;
    }

    public double getBPRecvDiastolicRight() throws JSONException {
        return Math.round(json.getDouble("BPRecvDiastolicRight")*100d)/100d;
    }

    public double getBPRecvMeanLeft() throws JSONException {
        return Math.round(json.getDouble("BPRecvMeanLeft")*100d)/100d;
    }

    public double getBPRecvMeanRight() throws JSONException {
        return Math.round(json.getDouble("BPRecvMeanRight")*100d)/100d;
    }

    public double getBPRecvPulseRateLeft() throws JSONException {
        return Math.round(json.getDouble("BPRecvPulseRateLeft")*100d)/100d;
    }

    public double getBPRecvPulseRateRight() throws JSONException {
        return Math.round(json.getDouble("BPRecvPulseRateRight")*100d)/100d;
    }

    public double getBPRecvSystolicLeft() throws JSONException {
        return Math.round(json.getDouble("BPRecvSystolicLeft")*100d)/100d;
    }

    public double getBPRecvSystolicRight() throws JSONException {
        return Math.round(json.getDouble("BPRecvSystolicRight")*100d)/100d;
    }

    public double getBPRestDiastolicLeft() throws JSONException {
        return Math.round(json.getDouble("BPRestDiastolicLeft")*100d)/100d;
    }

    public double getBPRestDiastolicRight() throws JSONException {
        return Math.round(json.getDouble("BPRestDiastolicRight")*100d)/100d;
    }

    public double getBPRestMeanLeft() throws JSONException {
        return Math.round(json.getDouble("BPRestMeanLeft")*100d)/100d;
    }

    public double getBPRestMeanRight() throws JSONException {
        return Math.round(json.getDouble("BPRestMeanRight")*100d)/100d;
    }

    public double getBPRestPulseRateLeft() throws JSONException {
        return Math.round(json.getDouble("BPRestPulseRateLeft")*100d)/100d;
    }

    public double getBPRestPulseRateRight() throws JSONException {
        return Math.round(json.getDouble("BPRestPulseRateRight")*100d)/100d;
    }

    public double getBPRestSystolicLeft() throws JSONException {
        return Math.round(json.getDouble("BPRestSystolicLeft")*100d)/100d;
    }

    public double getBPRestSystolicRight() throws JSONException {
        return Math.round(json.getDouble("BPRestSystolicRight")*100d)/100d;
    }

    public double getBPStimDiastolicLeft() throws JSONException {
        return Math.round(json.getDouble("BPStimDiastolicLeft")*100d)/100d;
    }

    public double getBPStimDiastolicRight() throws JSONException {
        return Math.round(json.getDouble("BPStimDiastolicRight")*100d)/100d;
    }

    public double getBPStimMeanLeft() throws JSONException {
        return Math.round(json.getDouble("BPStimMeanLeft")*100d)/100d;
    }

    public double getBPStimMeanRight() throws JSONException {
        return Math.round(json.getDouble("BPStimMeanRight")*100d)/100d;
    }

    public double getBPStimPulseRateLeft() throws JSONException {
        return Math.round(json.getDouble("BPStimPulseRateLeft")*100d)/100d;
    }

    public double getBPStimPulseRateRight() throws JSONException {
        return Math.round(json.getDouble("BPStimPulseRateRight")*100d)/100d;
    }

    public double getBPStimSystolicLeft() throws JSONException {
        return Math.round(json.getDouble("BPStimSystolicLeft")*100d)/100d;
    }

    public double getBPStimSystolicRight() throws JSONException {
        return Math.round(json.getDouble("BPStimSystolicRight")*100d)/100d;
    }

    public double getBalancePer() throws JSONException {
        return Math.round(json.getDouble("BalancePer")*100d)/100d;
    }

    public String getBirthdate() throws JSONException {
        return json.getString("Birthdate");
    }

    public double getBmiPer() throws JSONException {
        return Math.round(json.getDouble("BmiPer")*100d)/100d;
    }

    public double getBodyAge() throws JSONException {
        return Math.round(json.getDouble("BodyAge")*100d)/100d;
    }

    public double getBodyFatPer() throws JSONException {
        return Math.round(json.getDouble("BodyFatPer")*100d)/100d;
    }

    public double getBodyFatPercent() throws JSONException {
        return Math.round(json.getDouble("BodyFatPercent")*100d)/100d;
    }

    public double getBodyMineralPer() throws JSONException {
        return Math.round(json.getDouble("BodyMineralPer")*100d)/100d;
    }

    public double getBodyProtein() throws JSONException {
        return Math.round(json.getDouble("BodyProtein")*100d)/100d;
    }

    public double getBodySkeletalPer() throws JSONException {
        return Math.round(json.getDouble("BodySkeletalPer")*100d)/100d;
    }

    public double getBodyWaterPer() throws JSONException {
        return Math.round(json.getDouble("BodyWaterPer")*100d)/100d;
    }

    public double getCalorie() throws JSONException {
        return Math.round(json.getDouble("Calorie")*100d)/100d;
    }

    public double getCardioAge() throws JSONException {
        return Math.round(json.getDouble("CardioAge")*100d)/100d;
    }

    public double getCardioWattLoad() throws JSONException {
        return Math.round(json.getDouble("CardioWattLoad")*100d)/100d;
    }

    public double getFat() throws JSONException {
        return Math.round(json.getDouble("Fat")*100d)/100d;
    }

    public double getFitnessAge() throws JSONException {
        return Math.round(json.getDouble("FitnessAge")*100d)/100d;
    }

    public double getFitnessWatt() throws JSONException {
        return Math.round(json.getDouble("FitnessWatt")*100d)/100d;
    }

    public double getFlexLeft() throws JSONException {
        return Math.round(json.getDouble("FlexLeft")*100d)/100d;
    }

    public double getFlexLeftPer() throws JSONException {
        return Math.round(json.getDouble("FlexLeftPer")*100d)/100d;
    }

    public double getFlexRight() throws JSONException {
        return Math.round(json.getDouble("FlexRight")*100d)/100d;
    }

    public double getFlexRightPer() throws JSONException {
        return Math.round(json.getDouble("FlexRightPer")*100d)/100d;
    }

    public String getGender() throws JSONException {
        return json.getString("Gender");
    }

    public double getHRRecv() throws JSONException {
        return Math.round(json.getDouble("HRRecv")*100d)/100d;
    }

    public double getHRRest() throws JSONException {
        return Math.round(json.getDouble("HRRest")*100d)/100d;
    }

    public double getHRStim() throws JSONException {
        return Math.round(json.getDouble("HRStim")*100d)/100d;
    }

    public double getHandEnduranceLeft() throws JSONException {
        return Math.round(json.getDouble("HandEnduranceLeft")*100d)/100d;
    }

    public double getHandEnduranceLeftPer() throws JSONException {
        return Math.round(json.getDouble("HandEnduranceLeftPer")*100d)/100d;
    }

    public double getHandEnduranceRight() throws JSONException {
        return Math.round(json.getDouble("HandEnduranceRight")*100d)/100d;
    }

    public double getHandEnduranceRightPer() throws JSONException {
        return Math.round(json.getDouble("HandEnduranceRightPer")*100d)/100d;
    }

    public double getHandMaxKgfLeft() throws JSONException {
        return Math.round(json.getDouble("HandMaxKgfLeft")*100d)/100d;
    }

    public double getHandMaxKgfRight() throws JSONException {
        return Math.round(json.getDouble("HandMaxKgfRight")*100d)/100d;
    }

    public double getHandStrengthLeft() throws JSONException {
        return Math.round(json.getDouble("HandStrengthLeft")*100d)/100d;
    }

    public double getHandStrengthLeftPer() throws JSONException {
        return Math.round(json.getDouble("HandStrengthLeftPer")*100d)/100d;
    }

    public double getHandStrengthRight() throws JSONException {
        return Math.round(json.getDouble("HandStrengthRight")*100d)/100d;
    }

    public double getHandStrengthRightPer() throws JSONException {
        return Math.round(json.getDouble("HandStrengthRightPer")*100d)/100d;
    }

    public double getHeight() throws JSONException {
        return Math.round(json.getDouble("Height")*100d)/100d;
    }

    public double getHeightPer() throws JSONException {
        return Math.round(json.getDouble("HeightPer")*100d)/100d;
    }

    public double getDoubleraCellularWater() throws JSONException {
        return Math.round(json.getDouble("doubleraCellularWater")*100d)/100d;
    }

    public double getLeftArm_BodyFatPercent() throws JSONException {
        return Math.round(json.getDouble("LeftArm_BodyFatPercent")*100d)/100d;
    }

    public double getLeftArm_ExtraCellularWater() throws JSONException {
        return Math.round(json.getDouble("LeftArm_ExtraCellularWater")*100d)/100d;
    }

    public double getLeftArm_doubleraCellularWater() throws JSONException {
        return Math.round(json.getDouble("LeftArm_doubleraCellularWater")*100d)/100d;
    }

    public double getLeftArm_Mineral() throws JSONException {
        return Math.round(json.getDouble("LeftArm_Mineral")*100d)/100d;
    }

    public double getLeftArm_Protein() throws JSONException {
        return Math.round(json.getDouble("LeftArm_Protein")*100d)/100d;
    }

    public double getLeftArm_SkeletalMuscle() throws JSONException {
        return Math.round(json.getDouble("LeftArm_SkeletalMuscle")*100d)/100d;
    }

    public double getLeftLeg_BodyFatPercent() throws JSONException {
        return Math.round(json.getDouble("LeftLeg_BodyFatPercent")*100d)/100d;
    }

    public double getLeftLeg_ExtraCellularWater() throws JSONException {
        return Math.round(json.getDouble("LeftLeg_ExtraCellularWater")*100d)/100d;
    }

    public double getLeftLeg_doubleraCellularWater() throws JSONException {
        return Math.round(json.getDouble("LeftLeg_doubleraCellularWater")*100d)/100d;
    }

    public double getLeftLeg_Mineral() throws JSONException {
        return Math.round(json.getDouble("LeftLeg_Mineral")*100d)/100d;
    }

    public double getLeftLeg_Protein() throws JSONException {
        return Math.round(json.getDouble("LeftLeg_Protein")*100d)/100d;
    }

    public double getLeftLeg_SkeletalMuscle() throws JSONException {
        return Math.round(json.getDouble("LeftLeg_SkeletalMuscle")*100d)/100d;
    }

    public double getLegEnduranceLeft() throws JSONException {
        return Math.round(json.getDouble("LegEnduranceLeft")*100d)/100d;
    }

    public double getLegEnduranceLeftPer() throws JSONException {
        return Math.round(json.getDouble("LegEnduranceLeftPer")*100d)/100d;
    }

    public double getLegEnduranceRight() throws JSONException {
        return Math.round(json.getDouble("LegEnduranceRight")*100d)/100d;
    }

    public double getLegEnduranceRightPer() throws JSONException {
        return Math.round(json.getDouble("LegEnduranceRightPer")*100d)/100d;
    }

    public double getLegStrengthLeft() throws JSONException {
        return Math.round(json.getDouble("LegStrengthLeft")*100d)/100d;
    }

    public double getLegStrengthLeftPer() throws JSONException {
        return Math.round(json.getDouble("LegStrengthLeftPer")*100d)/100d;
    }

    public double getLegStrengthRight() throws JSONException {
        return Math.round(json.getDouble("LegStrengthRight")*100d)/100d;
    }

    public double getLegStrengthRightPer() throws JSONException {
        return Math.round(json.getDouble("LegStrengthRightPer")*100d)/100d;
    }

    public String getMedifitTestGUID() throws JSONException {
        return json.getString("MedifitTestGUID");
    }

    public double getMineral() throws JSONException {
        return Math.round(json.getDouble("Mineral")*100d)/100d;
    }

    public double getOpenCenterX() throws JSONException {
        return Math.round(json.getDouble("OpenCenterX")*100d)/100d;
    }

    public double getOpenCenterY() throws JSONException {
        return Math.round(json.getDouble("OpenCenterY")*100d)/100d;
    }

    public double getOpenEnvelopArea() throws JSONException {
        return Math.round(json.getDouble("OpenEnvelopArea")*100d)/100d;
    }

    public String getPhoneNumber() throws JSONException {
        return json.getString("PhoneNumber");
    }

    public double getPower() throws JSONException {
        return Math.round(json.getDouble("Power")*100d)/100d;
    }

    public double getPowerPer() throws JSONException {
        return Math.round(json.getDouble("PowerPer")*100d)/100d;
    }

    public double getProtein() throws JSONException {
        return Math.round(json.getDouble("Protein")*100d)/100d;
    }

    public double getReactionLeftPer() throws JSONException {
        return Math.round(json.getDouble("ReactionLeftPer")*100d)/100d;
    }

    public double getReactionRightPer() throws JSONException {
        return Math.round(json.getDouble("ReactionRightPer")*100d)/100d;
    }

    public double getResponseLeftPer() throws JSONException {
        return Math.round(json.getDouble("ResponseLeftPer")*100d)/100d;
    }

    public double getResponseRightPer() throws JSONException {
        return Math.round(json.getDouble("ResponseRightPer")*100d)/100d;
    }

    public double getRightArm_BodyFatPercent() throws JSONException {
        return Math.round(json.getDouble("RightArm_BodyFatPercent")*100d)/100d;
    }

    public double getRightArm_ExtraCellularWater() throws JSONException {
        return Math.round(json.getDouble("RightArm_ExtraCellularWater")*100d)/100d;
    }

    public double getRightArm_doubleraCellularWater() throws JSONException {
        return Math.round(json.getDouble("RightArm_doubleraCellularWater")*100d)/100d;
    }

    public double getRightArm_Mineral() throws JSONException {
        return Math.round(json.getDouble("RightArm_Mineral")*100d)/100d;
    }

    public double getRightArm_Protein() throws JSONException {
        return Math.round(json.getDouble("RightArm_Protein")*100d)/100d;
    }

    public double getRightArm_SkeletalMuscle() throws JSONException {
        return Math.round(json.getDouble("RightArm_SkeletalMuscle")*100d)/100d;
    }

    public double getRightLeg_BodyFatPercent() throws JSONException {
        return Math.round(json.getDouble("RightLeg_BodyFatPercent")*100d)/100d;
    }

    public double getRightLeg_ExtraCellularWater() throws JSONException {
        return Math.round(json.getDouble("RightLeg_ExtraCellularWater")*100d)/100d;
    }

    public double getRightLeg_doubleraCellularWater() throws JSONException {
        return Math.round(json.getDouble("RightLeg_doubleraCellularWater")*100d)/100d;
    }

    public double getRightLeg_Mineral() throws JSONException {
        return Math.round(json.getDouble("RightLeg_Mineral")*100d)/100d;
    }

    public double getRightLeg_Protein() throws JSONException {
        return Math.round(json.getDouble("RightLeg_Protein")*100d)/100d;
    }

    public double getRightLeg_SkeletalMuscle() throws JSONException {
        return Math.round(json.getDouble("RightLeg_SkeletalMuscle")*100d)/100d;
    }

    public double getRombergQuotient() throws JSONException {
        return Math.round(json.getDouble("RombergQuotient")*100d)/100d;
    }

    public double getSeatPosition() throws JSONException {
        return Math.round(json.getDouble("SeatPosition")*100d)/100d;
    }

    public double getSkeletalMuscle() throws JSONException {
        return Math.round(json.getDouble("SkeletalMuscle")*100d)/100d;
    }

    public double getStandingLeft() throws JSONException {
        return Math.round(json.getDouble("StandingLeft")*100d)/100d;
    }

    public double getStandingRight() throws JSONException {
        return Math.round(json.getDouble("StandingRight")*100d)/100d;
    }

    public double getTargetHR() throws JSONException {
        return Math.round(json.getDouble("TargetHR")*100d)/100d;
    }

    public String getTestDate() throws JSONException {
        return json.getString("TestDate");
    }

    public String getTestID() throws JSONException {
        return json.getString("TestID");
    }

    public String getTestType() throws JSONException {
        return json.getString("TestType");
    }

    public double getTravelDistance() throws JSONException {
        return Math.round(json.getDouble("TravelDistance")*100d)/100d;
    }

    public double getTrunk_BodyFatPercent() throws JSONException {
        return Math.round(json.getDouble("Trunk_BodyFatPercent")*100d)/100d;
    }

    public double getTrunk_ExtraCellularWater() throws JSONException {
        return Math.round(json.getDouble("Trunk_ExtraCellularWater")*100d)/100d;
    }

    public double getTrunk_DoubleraCellularWater() throws JSONException {
        return Math.round(json.getDouble("Trunk_DoubleraCellularWater")*100d)/100d;
    }

    public double getTrunk_Mineral() throws JSONException {
        return Math.round(json.getDouble("Trunk_Mineral")*100d)/100d;
    }

    public double getTrunk_Protein() throws JSONException {
        return Math.round(json.getDouble("Trunk_Protein")*100d)/100d;
    }

    public double getTrunk_SkeletalMuscle() throws JSONException {
        return Math.round(json.getDouble("Trunk_SkeletalMuscle")*100d)/100d;
    }

    public String getUserAge() throws JSONException {
        return json.getString("UserAge");
    }

    public String getUserID() throws JSONException {
        return json.getString("UserID");
    }

    public String getUserName() throws JSONException {
        return json.getString("UserName");
    }

    public double getVo2Max() throws JSONException {
        return Math.round(json.getDouble("Vo2Max")*100d)/100d;
    }

    public double getVo2maxPer() throws JSONException {
        return Math.round(json.getDouble("Vo2maxPer")*100d)/100d;
    }

    public double getWaist() throws JSONException {
        return Math.round(json.getDouble("Waist")*100d)/100d;
    }

    public double getWaistPer() throws JSONException {
        return Math.round(json.getDouble("WaistPer")*100d)/100d;
    }

    public double getWeight() throws JSONException {
        return Math.round(json.getDouble("Weight")*100d)/100d;
    }

    public double getWeightLeftBottomPer() throws JSONException {
        return Math.round(json.getDouble("WeightLeftBottomPer")*100d)/100d;
    }

    public double getWeightLeftTopPer() throws JSONException {
        return Math.round(json.getDouble("WeightLeftTopPer")*100d)/100d;
    }

    public double getWeightPer() throws JSONException {
        return Math.round(json.getDouble("WeightPer")*100d)/100d;
    }

    public double getWeightRightBottomPer() throws JSONException {
        return Math.round(json.getDouble("WeightRightBottomPer")*100d)/100d;
    }

    public double getWeightRightTopPer() throws JSONException {
        return Math.round(json.getDouble("WeightRightTopPer")*100d)/100d;
    }

    public double getYongNormalPer() throws JSONException {
        return Math.round(json.getDouble("YongNormalPer")*100d)/100d;
    }

    public double getYoungNormal() throws JSONException {
        return Math.round(json.getDouble("YoungNormal")*100d)/100d;
    }



    public int getFatigueINdex() throws JSONException {
        return json.getInt("FatigueIndex");
    }

    public int getFatigueSlope() throws JSONException {
        return json.getInt("FatigueSlope");
    }

    public int getPowerLowest() throws JSONException {
        return json.getInt("PowerLowest");
    }

    public int getPowerMean() throws JSONException {
        return json.getInt("PowerMean");
    }

    public int getPowerPeak() throws JSONException {
        return json.getInt("PowerPeak");
    }

    public int getPowerPeakWKG() throws JSONException {
        return json.getInt("PowerPeakWKG");
    }




    public String UserName() throws JSONException{
        return json.getString("UserName");
    }

    public String Birthdate() throws JSONException {
        return json.getString("Birthdate");
    }

    public int getFlexion()  throws JSONException {
        return json.getInt("Flexion");
    }

    public int getExtension()  throws JSONException {
        return json.getInt("Extension");
    }
    public int geLateralFlexionLeft()  throws JSONException {
        return json.getInt("LateralFlexionLeft");
    }
    public int getLateralFlexionRight()  throws JSONException {
        return json.getInt("LateralFlexionRight");
    }
    public int getRotationLeft()  throws JSONException {
        return json.getInt("RotationLeft");
    }
    public int getRotationRight()  throws JSONException {
        return json.getInt("RotationRight");
    }

    public double insert(String name, String LRdata) throws JSONException {
        return Math.round(json.getDouble(name+LRdata)*100d)/100d;
    }

    //For Graph Usage
    public int getBloodPressure(String strTestSite, String strSubjectState, String strBpValue) throws JSONException {
        return json.getInt("BP"+strSubjectState+strBpValue+strTestSite);
    }

    //For Graph Usage
    public int getHeartRate(String strSubjectState) throws JSONException {
        return json.getInt("HR"+strSubjectState);
    }
    public int getFlexLR(String strValueIem, String strTestSite) throws JSONException {
        return json.getInt(strValueIem+strTestSite);
    } //Rotation, Bending
    public int getFlexLR(String strValueIem) throws JSONException {
        return json.getInt(strValueIem);
    } //Extension, Flexion

    public int getStrength(String state, String name) throws  JSONException {
        return json.getInt(name+state);
    }

    public double getAgility(String state, String name) throws  JSONException{
        double Agility = 0;
        if(name == "UB" && state == "Left")
            Agility = UBLeft();
        else if(name == "UB" && state == "Right")
            Agility = UBRight();
        else if(name =="UL" && state == "Left")
            Agility = ULLeft();
        else if(name == "UL" && state == "Right")
            Agility = ULRight();
        return Agility;
    }

    public double UBLeft() throws JSONException{
        return getAgilityReactionUBLeft() + getAgilityMovementUBLeft();
    }

    public double UBRight() throws JSONException{
        return getAgilityReactionUBRight() + getAgilityMovementUBRight();
    }

    public double ULLeft() throws JSONException{
        return getAgilityReactionULLeft() + getAgilityMovementULLeft();
    }

    public double ULRight() throws JSONException{
        return getAgilityReactionULRight() + getAgilityMovementULRight();
    }

    public JSONArray getRoutine() throws JSONException {
        return converter.checking(json.get("routine"));
    }
    public String get_Guideline() throws JSONException {
        return json.getString("Guideline");
    }
    public String get_Prescription() throws JSONException {
        return json.getString("Prescription");
    }
    public JSONObject get_prescriptions() throws JSONException {
        return json.getJSONObject("prescription");
    }
    public JSONObject get_guidelines() throws JSONException {
        return json.getJSONObject("guidelines");
    }

    public JSONObject getExercises() throws JSONException {
        return json.getJSONObject("exercises");
    }

    public JSONArray get_guidelineConvert() throws JSONException {
        return converter.checking(json.get("guideline"));
    }

    public String getTitle() throws JSONException {
        return json.getString("title");
    }
    public String getDate() throws JSONException {
        return json.getString("Date");
    }
    public JSONArray get_ExerciseRoutineConvert() throws JSONException {
        return converter.checking(json.get("exercise-routine"));
    }
    public JSONArray getExercise_Unit() throws JSONException {
        return converter.checking(json.get("exercise-unit"));
    }

    public String getPrescription_id()  throws JSONException {
        return json.getString("id");
    }

    public String getSession() throws JSONException {
        return json.getString("session");
    }

    public String getPhase()  throws JSONException {
        return json.getString("phase");
    }

    public String getType()  throws JSONException {
        return json.getString("type");
    }

    public String getBodypart()  throws JSONException {
        return json.getString("bodypart");
    }

    public String getBodypart2()  throws JSONException {
        return json.getString("bodypart2");
    }

    public String getEquipment() throws JSONException {
        return json.getString("equipment");
    }

    public String get_Description()  throws JSONException {
        return json.getString("description");
    }

    public String  getBenefit() throws JSONException {
        return json.getString("benefit");
    }

    public String getCauiton()  throws JSONException {
        return json.getString("caution");
    }

    public String getImage()  throws JSONException {
        return json.getString("image");
    }

    public String getVideo()  throws JSONException {
        return json.getString("video");
    }

    public String getSet()  throws JSONException {
        return json.getString("set");
    }

    public String getRepetition() throws JSONException {
        return json.getString("repetition");
    }

    public String getTime()  throws JSONException {
        return json.getString("time");
    }

    public String getIntensity()  throws JSONException {
        return json.getString("intensity");
    }

    public String getWeek() throws JSONException {
        return json.getString("week");
    }

    class check{
        public JSONArray checking(Object obj){
            if(obj instanceof JSONArray) {
                return (JSONArray)obj;
            }
            else if(obj instanceof JSONObject) {
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(obj);
                return jsonArray;
            }
            return null;
        }
    }
}
