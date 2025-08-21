package vitals;

import java.util.HashMap;
import java.util.Map;

public abstract class VitalsChecker {

  enum VitalStatus {
    CRITICAL, WARNING, NORMAL
  }

  // Configuration for each vital
  static class VitalRange {
    final float lower;
    final float upper;
    final float tolerancePercent;

    VitalRange(float lower, float upper, float tolerancePercent) {
      this.lower = lower;
      this.upper = upper;
      this.tolerancePercent = tolerancePercent;
    }
  }

  static Map<String, VitalRange> vitalRanges = new HashMap<>();
  static {
    vitalRanges.put("temperature", new VitalRange(95, 102, 1.5f));
    vitalRanges.put("pulse", new VitalRange(60, 100, 1.5f));
    vitalRanges.put("spo2", new VitalRange(90, 100, 1.5f));
  }

  // --- Transformation 1: classify vital ---
  static VitalStatus classify(String vitalName, float value) {
    VitalRange range = vitalRanges.get(vitalName);
    float tolerance = (range.upper * range.tolerancePercent) / 100;

    if (value < range.lower || value > range.upper) {
      return VitalStatus.CRITICAL;
    }
    if ((value >= range.lower && value <= range.lower + tolerance) ||
        (value <= range.upper && value >= range.upper - tolerance)) {
      return VitalStatus.WARNING;
    }
    return VitalStatus.NORMAL;
  }

  // --- Transformation 2: message mapping ---
  static String message(String vitalName, V
