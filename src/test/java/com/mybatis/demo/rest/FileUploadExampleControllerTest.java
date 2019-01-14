/**
 * 
 */
package com.multi.module.services.web.intra;

import static com.multi.module.services.util.AppConstants.INTRA_MY_SERVICE_ENDPOINT;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.multi.module.service.BarService;
import com.multi.module.service.MyIntraService;
import com.multi.module.services.util.IdentifyFileMimeTypeForUpload;

/**
 * @author Michael Somers
 *
 */
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FooIntraControllerTest {

	 @Mock 
	 private HttpServletRequest mockHttpServletRequest;
	 @Mock
	 private MyIntraService mockMyIntraService;
	 @Mock  
	 private BarService mockBarService;
	 @Mock
	 private IdentifyFileMimeTypeForUpload  idFile;
	 
	 private FooIntraController fooIntraController;
	 private static final Logger LOG = LoggerFactory.getLogger(FooIntraControllerTest.class);

	 private final String SYSTEM_PATH = System.getProperty("user.dir");
	 private final String FILE_PATH = SYSTEM_PATH.concat("/src/test/resources/data");
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		fooIntraController = new FooIntraController(idFile, mockMyIntraService, mockBarService);
	    SecurityProperties.User user = new SecurityProperties.User();
		String contextUser = user.getName();        
		UsernamePasswordAuthenticationToken auth  = new UsernamePasswordAuthenticationToken(contextUser, null);
	    SecurityContextHolder.getContext().setAuthentication(auth);
	    //now we can use the contextUser as the user that logged in and find roles or entitlements
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.multi.module.services.web.intra.FooIntraController#FooIntraController(com.multi.module.service.MyIntraService, com.multi.module.service.BarService)}.
	 * @throws IOException 
	 */
	@Test
	public final void testFooIntraController() throws IOException {
		File file = new File(FILE_PATH.concat("/testdata.txt"));
		FileInputStream fil = new FileInputStream(file);
		//LOG.debug("length: {} exist:{}",file.length(),file.exists());
		MockMultipartFile fileTopload =  new MockMultipartFile("file", file.getName(),"multipart/form-data",fil);
		when(mockHttpServletRequest.getAttribute(anyString())).thenReturn(INTRA_MY_SERVICE_ENDPOINT);
		//when(findSecureUser()).return secureuser
		LOG.debug( "{}",fooIntraController.apiCallEndPointAction( fileTopload));
	}
	
	@Test
	public final void testFooIntraBController() throws IOException {
		File file = new File(FILE_PATH.concat("/testdata2.txt"));
		FileInputStream fil = new FileInputStream(file);
		//LOG.debug("length: {} exist:{}",file.length(),file.exists());
		MockMultipartFile fileTopload =  new MockMultipartFile("file", file.getName(),"multipart/form-data",fil);
		when(mockHttpServletRequest.getAttribute(anyString())).thenReturn(INTRA_MY_SERVICE_ENDPOINT);
		//when(findSecureUser()).return secureuser
		LOG.debug( "{}",fooIntraController.apiCallEndPointAction( fileTopload));
	}
}
