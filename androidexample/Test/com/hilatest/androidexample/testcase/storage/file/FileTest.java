package com.hilatest.androidexample.testcase.storage.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.hilatest.androidexample.C;
import com.hilatest.androidexample.R;
import com.hilatest.androidexample.testcase.CommonTest;

public class FileTest extends CommonTest {

	private final static String CONTEXTFILENAME = "hilatestexample.txt";
	/**
	 * 这段代码的file会在
	 * data/data/[PACKAGE_NAME]/files/file.name中
	 * 然后其实这是在内存中的一种。跟着软件走的。
	 * 不能创建子目录
	 */
	public void testContextFile(){
		FileOutputStream fos = null;
		
		 try {
             fos = this.getActivity().openFileOutput(CONTEXTFILENAME,
               Context.MODE_PRIVATE);
             fos.write("success".getBytes());
         } catch (FileNotFoundException e) {
             Log.e("CreateFile", e.getLocalizedMessage());
         } catch (IOException e) {
             Log.e("CreateFile", e.getLocalizedMessage());
         } finally {
             if (fos != null) {
                 try {
                     fos.flush();
                     fos.close();
                 } catch (IOException e) {
                     // swallow
                 }
             }
         }
	}
	
	public void testAccessContextFile(){
		FileInputStream fis = null;
		
		try {
            fis = this.getActivity().openFileInput(CONTEXTFILENAME);
            byte[] reader = new byte[fis.available()];
            while (fis.read(reader) != -1) {}
            Log.i(C.Applicatin,new String(reader));
        } catch (IOException e) {
            Log.e("ReadFile", e.getMessage(), e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    // swallow
                }
            }
        }
	}
	
	public void testAccessRawFile(){
		InputStream is = null;
        try {
            is = this.getActivity().getResources().openRawResource(R.raw.raw_file_test);
            byte[] reader = new byte[is.available()];
            while (is.read(reader) != -1) {}
            Log.i(C.Applicatin,new String(reader));
        } catch (IOException e) {
            Log.e("ReadRawResourceFile", e.getMessage(), e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // swallow
                }
            }
        }
	}
	
	public void testXMLFile(){
		 XmlPullParser parser = this.getActivity().getResources().getXml(R.xml.xml_test);
		 
		 StringBuffer sb = new StringBuffer();
	        try {
	            while (parser.next() != XmlPullParser.END_DOCUMENT) {
	                String name = parser.getName();
	                String first = null;
	                String last = null;
	                if ((name != null) && name.equals("person")) {
	                    int size = parser.getAttributeCount();
	                    for (int i = 0; i < size; i++) {
	                        String attrName = 
	                          parser.getAttributeName(i);
	                        String attrValue = 
	                          parser.getAttributeValue(i);
	                        if ((attrName != null) 
	                          && attrName.equals("firstname")) {
	                            first = attrValue;
	                        } else if ((attrName != null) 
	                          && attrName.equals("lastname")) {
	                            last = attrValue;
	                        }
	                    }
	                    if ((first != null) && (last != null)) {
	                        sb.append(last + ", " + first + "\n");
	                    }
	                }
	            }
	            Log.i(C.Applicatin,sb.toString());
	        } catch (Exception e) {
	            Log.e(C.Applicatin, e.getMessage(), e);
	        }
	}
	
	/**
	 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
	 */
	public void testSDCard(){

        String fileName = "testfile-"+ System.currentTimeMillis() + ".txt";
        File sdDir = Environment.getExternalStorageDirectory(); 
        Log.i(C.Applicatin, "sddir exists:"+sdDir.exists());
        Log.i(C.Applicatin, "sddir can write:"+sdDir.canWrite());
        if (sdDir.exists() && sdDir.canWrite()) {
            File uadDir = new File(sdDir.getAbsolutePath() 
              + "/hilatestexample");
            uadDir.mkdir();
            if (uadDir.exists() && uadDir.canWrite()) {
                File file = new File(uadDir.getAbsolutePath() 
                  + "/" + fileName);
                try {
                    file.createNewFile(); 
                } catch (IOException e) {
                    // log and or handle
                }
                if (file.exists() && file.canWrite()) {
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(file);
                        fos.write("I fear you speak upon the rack,where men enforced do speak anything.".getBytes());
                    } catch (FileNotFoundException e) {
                        Log.e(C.Applicatin, "ERROR", e);
                    } catch (IOException e) {
                        Log.e(C.Applicatin, "ERROR", e);
                    } finally {
                        if (fos != null) {
                            try {
                                fos.flush();
                                fos.close();
                            } catch (IOException e) {
                                // swallow
                            }
                        }
                    }
                } else {
                    // log and or handle - error writing to file
                }
            } else {
                // log and or handle - 
                // unable to write to /sdcard/unlocking_android
            }
        } else {
            Log.e(C.Applicatin,
            	"ERROR /sdcard path not available (did you create  an SD image with the mksdcard tool, and start emulator with -sdcard <path_to_file> option?");
       }
        
       File rFile = 
         new File("/sdcard/hilatestexample/" + fileName);
       if (rFile.exists() && rFile.canRead()) {
           FileInputStream fis = null;
           try {
               fis = new FileInputStream(rFile);
               byte[] reader = new byte[fis.available()];
               while (fis.read(reader) != -1) {
               }
               Log.i(C.Applicatin,new String(reader));
           } catch (IOException e) {
               // log and or handle
           } finally {
               if (fis != null) {
                   try {
                       fis.close();
                   } catch (IOException e) {
                       // swallow
                   }
               }
           }
       } else {
    	   Log.i(C.Applicatin,
             "Unable to read/write sdcard file, see logcat output");
       }

	}
}
