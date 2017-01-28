package com.shop.service.storage;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.shop.util.ImageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

/**
 * Created by vazgen on 11/30/16.
 */
@Service
public class AzureImageStorage implements ImageStorage {


    public static final String storageConnectionString = "DefaultEndpointsProtocol=https;AccountName=vuvustorage;AccountKey=QtR4SWlAcLntfTX5tqE2ky14CFBOfCUyh2fUFpKZl8jkaGc9NzJ45Zjn5cdzGTKYTqa2FXCiwKBE4UlA6Sjerg==";

    private static int imageSize=600;
    private CloudStorageAccount storageAccount = null;


    AzureImageStorage() {

        try {
            storageAccount = CloudStorageAccount.parse(storageConnectionString);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void save(MultipartFile file, String name, String extension) throws ImageStorageException {

        // Create the blob client.
        CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

        // Retrieve reference to a previously created container.
        CloudBlobContainer container = null;
        try {
            container = blobClient.getContainerReference("images");
        } catch (URISyntaxException e) {
            throw new ImageStorageException("Invalid Container name", e);
        } catch (StorageException e) {
            throw new ImageStorageException("Azure storage Exception", e);

        }

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(file.getInputStream());
        } catch (IOException e) {
            throw new ImageStorageException("Can't read file input stream", e);

        }
        BufferedImage scaledImage = ImageUtil.resize(bufferedImage, imageSize);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(scaledImage, extension, os);
        } catch (IOException e) {
            throw new ImageStorageException("Can't write scaledImage", e);

        }
        InputStream is = new ByteArrayInputStream(os.toByteArray());

        // Create or overwrite the "myimage.jpg" blob with contents from a local file.
        CloudBlockBlob blob = null;
        try {
            blob = container.getBlockBlobReference(name + "."+extension);
        } catch (URISyntaxException e) {
            throw new ImageStorageException("Can't get Blob reference by name scaledImage", e);

        } catch (StorageException e) {
            throw new ImageStorageException(e.getMessage(), e);

        }
        try {
            blob.upload(is, is.available());
        } catch (StorageException e) {
            throw new ImageStorageException(e.getMessage(), e);
        } catch (IOException e) {
        }

    }
}
