import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.bht.fpa.mail.common.model.MessageTest;
import de.bht.fpa.mail.common.testdata.FileSystemTestDataProviderTest;
import de.bht.fpa.mail.common.testdata.MessageTestDataProviderTest;
import de.bht.fpa.mail.common.testdata.RandomTestDataProviderTest;

@RunWith(Suite.class)
@SuiteClasses({ MessageTest.class, MessageTestDataProviderTest.class, RandomTestDataProviderTest.class,
    FileSystemTestDataProviderTest.class, })
public class AllTests {

}
