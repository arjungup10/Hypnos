package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestPlayer.class, TestPlayerLoop.class, TestSetVolumeRectanglesLoop.class })
public class TestLoops {

}
