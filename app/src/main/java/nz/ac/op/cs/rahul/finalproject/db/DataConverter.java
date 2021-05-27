package nz.ac.op.cs.rahul.finalproject.db;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class DataConverter {public static byte[] convertImage2ByteArray(Bitmap bitmap){
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.JPEG, 60, stream);
    return stream.toByteArray();
}

    /**
     *
     * @param array
     * @return
     */

    public static Bitmap converByteArray2Image(byte [] array){
        return BitmapFactory.decodeByteArray(array, 0, array.length);
    }
}