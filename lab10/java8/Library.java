import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Library {

    private KeyPairGenerator keyGen;
    private KeyPair pair;
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private Cipher cipher;


    public Library() throws NoSuchPaddingException, NoSuchAlgorithmException {
		System.out.println("Inside java 8");
        Cipher cipher = Cipher.getInstance("RSA");
    }
    public void genKey() throws NoSuchAlgorithmException, NoSuchProviderException {
        this.keyGen = KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(1024);
    }

    public void createKeys(String name) {
        this.pair = this.keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
        try {
            writeToFile("KeyPair/publicKey" + name, getPublicKey().getEncoded());
            writeToFile("KeyPair/privateKey" + name, getPrivateKey().getEncoded());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PublicKey setPublic(String filename) throws Exception {
        byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        this.publicKey = kf.generatePublic(spec);
        return kf.generatePublic(spec);
    }
    public PrivateKey setPrivate(String filename) throws Exception {
        byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        this.privateKey = kf.generatePrivate(spec);
        return kf.generatePrivate(spec);
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }

    public void writeToFile(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();
    }
    public String encryptMsg(String msg, PrivateKey privateKey) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        this.cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] encryptedMessage = this.cipher.doFinal(msg.getBytes(StandardCharsets.UTF_8));
        return String.valueOf(encryptedMessage);
    }

    public String decryptMsg(String msg, PublicKey publicKey) throws InvalidKeyException , BadPaddingException, IllegalBlockSizeException{
        this.cipher.init(Cipher.DECRYPT_MODE, privateKey);
        String decryptedMessage = new String(this.cipher.doFinal(msg.getBytes()), StandardCharsets.UTF_8);
        return decryptedMessage;
    }


}
