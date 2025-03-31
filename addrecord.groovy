import java.io.IOException;
import groovy.io.FileType;
import org.unesco.jisis.corelib.common.IConnection
import org.unesco.jisis.corelib.client.ConnectionNIO
import org.unesco.jisis.corelib.client.ClientDbProxy
import org.unesco.jisis.corelib.common.IDatabase
import org.unesco.jisis.corelib.common.Global
import org.unesco.jisis.corelib.record.IRecord
import org.unesco.jisis.corelib.record.IField
import org.unesco.jisis.corelib.record.StringOccurrence
import org.unesco.jisis.corelib.record.Subfield

def addrec() {
    // Initialize the server parameters
   username = "admin";
   password = "admin";
   port     = "1111";
   hostname = "localhost";
   // Establish a connection to the server
   def connection =  ConnectionNIO.connect(hostname,Integer.valueOf(port),username, password);   
   // Create a Database object bind to this server     
  ClientDbProxy db = new ClientDbProxy(connection);
   // Let's use DB PhotoVova on root defined by DEF_HOME
   dbHome = "DEF_HOME";
   dbName = "PhotoArchive";
   // Open the databaseAll
   db.getDatabase(dbHome, dbName, Global. DATABASE_BULK_WRITE );
   // Get new record  

    IRecord rec1 = db.getRecord(1);
    IField fn10 = rec1.getField(10);
    IField fn20 = rec1.getField(20);

//  System.out.println(f10.getFieldValue());

//     IRecord rec1 = db.addNewRecord();

def list = [];
def hfolder = "Photo_all";
def dir = new File(hfolder)
dir.eachFileRecurse(FileType.DIRECTORIES) {file -> list <<file}
list.each{

    

       def f10 = it.name;

       def f20 = it.parent;
	   println f20.replace("\\", "/");
    fn10.setFieldValue(f10);
    fn20.setFieldValue(f20);    

//    System.out.println(fn10.getFieldValue());
    rec1.setMfn(0);
    db.addRecord(rec1);
    } //list
// Iterate over the records in the database until nomore

// Close the database
   db.close();
}
// call snippet1()
addrec();