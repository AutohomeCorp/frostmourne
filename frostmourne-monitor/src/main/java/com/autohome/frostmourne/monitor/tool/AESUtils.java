package com.autohome.frostmourne.monitor.tool;

import com.autohome.frostmourne.monitor.config.properties.EncryptProperties;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * AES utils
 *
 * @author limobo
 * @since 2022/9/9 14:48
 */
public final class AESUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AESUtils.class);

    private static final String DEFAULT_CIPHER_ALGORITHM = "SHA1PRNG";
    private static final String KEY_ALGORITHM = "AES";

    public static String encrypt(String data) {
        try {
            return doAES(data, Cipher.ENCRYPT_MODE);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            LOGGER.error("encrypt error with data: {}", data, e);
        } catch (Exception e) {
            LOGGER.error("encrypt exception with data: {}", data, e);
        }
        return null;
    }

    public static String decrypt(String data) {
        try {
            return doAES(data, Cipher.DECRYPT_MODE);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            LOGGER.error("decrypt error with data: {}", data, e);
        } catch (Exception e) {
            LOGGER.error("decrypt exception with data: {}", data, e);
        }
        return null;
    }

    private static String doAES(String data, int mode)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        boolean encrypt = mode == Cipher.ENCRYPT_MODE;
        byte[] content;
        if (encrypt) {
            content = data.getBytes(StandardCharsets.UTF_8);
        } else {
            content = Base64.decodeBase64(data.getBytes(StandardCharsets.UTF_8));
        }

        String key = EncryptProperties.getInstance().getKey();

        KeyGenerator generator = KeyGenerator.getInstance(KEY_ALGORITHM);
        SecureRandom secureRandom = SecureRandom.getInstance(DEFAULT_CIPHER_ALGORITHM);
        secureRandom.setSeed(key.getBytes(StandardCharsets.UTF_8));
        generator.init(128, secureRandom);
        SecretKeySpec keySpec = new SecretKeySpec(generator.generateKey().getEncoded(), KEY_ALGORITHM);

        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(mode, keySpec);
        byte[] result = cipher.doFinal(content);

        if (encrypt) {
            return Base64.encodeBase64String(result);
        } else {
            return new String(result, StandardCharsets.UTF_8);
        }
    }

}
