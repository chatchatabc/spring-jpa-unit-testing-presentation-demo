package org.spring.jpa.user.application.web.security;

import org.junit.jupiter.api.Test;
import org.spring.jpa.user.SpringBaseTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class EncryptionUtilsTest extends SpringBaseTest {


    final EncryptionUtils encryptionUtils;

    @Autowired
    private EncryptionUtilsTest(EncryptionUtils encryptionUtils) {
        this.encryptionUtils = encryptionUtils;
    }


    @Test
    void encryptTest() {
        String password = "123";
        assertThat(encryptionUtils.encrypt(password, encryptionUtils.generateSalt()).isEmpty()).isFalse();

    }
}