/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import empresa.software.tiendaonline.payload.URLImagenRequest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author pedro
 */
@Service
public class FirebaseDatabaseService {

    FileInputStream serviceAccount;

    FirebaseOptions options;
    
    FirebaseDatabase database = null;

    DatabaseReference ref;

    
    public FirebaseDatabaseService() {
//        try {
//            this.serviceAccount = new FileInputStream(Paths.get(".").toAbsolutePath().normalize().toString()+
//"/src/main/java/empresa/software/tiendaonline/service/tiendaonline-f6186-firebase-adminsdk-cubli-98a0f6bd67.json");
//            this.options = new FirebaseOptions.Builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .setDatabaseUrl("https://tiendaonline-f6186.firebaseio.com")
//                    .build();
//            FirebaseApp.initializeApp(options);
//            database = FirebaseDatabase.getInstance();
//            ref = database.getReference("server/saving-data/fireblog");
//        } catch (IOException ex) {
//            Logger.getLogger(FirebaseDatabaseService.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    public void SaveData(){
//        Map<String, URLImagenRequest> users = new HashMap<>();
//        DatabaseReference usersRef = ref.child("users");
//        users.put("alanisawesome", new URLImagenRequest("June 23, 1912 Alan Turing"));
//        usersRef.setValueAsync(users);

    }
}
