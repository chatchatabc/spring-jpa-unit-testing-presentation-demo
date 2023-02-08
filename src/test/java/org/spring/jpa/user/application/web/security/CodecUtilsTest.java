package org.spring.jpa.user.application.web.security;

import org.junit.jupiter.api.Test;
import org.spring.jpa.user.SpringBaseTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class CodecUtilsTest extends SpringBaseTest {


    final CodecUtils codecUtils;

    @Autowired
    private CodecUtilsTest(CodecUtils codecUtils) {
        this.codecUtils = codecUtils;
    }

    /**
     * AssertJ Test
     */
    @Test
    void encryptTest() {
        String password = "123";
        String hashed = codecUtils.hash(password, "2i3u423");
        System.out.println(hashed);
        assertThat(hashed.isEmpty()).isFalse();

    }
}