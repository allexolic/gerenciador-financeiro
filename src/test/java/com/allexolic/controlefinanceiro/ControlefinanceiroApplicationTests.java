package com.allexolic.controlefinanceiro;

import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import io.findify.s3mock.S3Mock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ControlefinanceiroApplicationTests {

	@Test
	void contextLoads() {
		S3Mock api = S3Mock.create(8001,"/tmp/s3");
		api.start();
		AmazonS3Client client = new AmazonS3Client((new AnonymousAWSCredentials()));
		client.setEndpoint("http://127.0.0.1:8001");
		client.createBucket("testbucket");
		client.putObject("testbucket", "file/name", "contents");
	}

}
