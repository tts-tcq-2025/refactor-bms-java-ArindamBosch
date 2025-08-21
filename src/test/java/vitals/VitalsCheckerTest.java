// package vitals;

// import static org.junit.Assert.assertFalse;
// import static org.junit.Assert.assertTrue;

// import org.junit.Test;


// public class VitalsCheckerTest {

//   @Test
//   public void testNotOkWhenAnyVitalIsOffRange() throws InterruptedException {
//     assertFalse(VitalsChecker.vitalsOk(99f, 102, 70));
//     assertTrue(VitalsChecker.vitalsOk(98.1f, 70, 98));
//   }
// }


package vitals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VitalsCheckerTest {

  @Test
  public void testCriticalCases() throws InterruptedException {
    assertFalse(VitalsChecker.vitalsOk(104f, 70, 95));  // high temp
    assertFalse(VitalsChecker.vitalsOk(98f, 40, 95));   // low pulse
    assertFalse(VitalsChecker.vitalsOk(98f, 70, 85));   // low spo2
  }

  @Test
  public void testWarningCases() throws InterruptedException {
    assertTrue(VitalsChecker.vitalsOk(95.5f, 70, 95));   // near hypothermia
    assertTrue(VitalsChecker.vitalsOk(101f, 99, 95));    // near hyperthermia
    assertTrue(VitalsChecker.vitalsOk(98f, 61, 91));     // near low spo2
  }

  @Test
  public void testNormalCase() throws InterruptedException {
    assertTrue(VitalsChecker.vitalsOk(98.1f, 70, 98));
  }
}

