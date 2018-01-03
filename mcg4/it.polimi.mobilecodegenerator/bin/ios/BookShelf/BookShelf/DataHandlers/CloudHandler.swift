//
//  CloudHandler.swift
//  BookShelf//
//  Automatically generated by MobileCodeGenerator 3.
//  Copyright � 2017 . All rights reserved.
//

import CloudKit

class CloudHandler{
	
	let container: CKContainer
	let publicDB: CKDatabase
	let privateDB: CKDatabase
	
	init() {
		
		container = CKContainer.default()
		publicDB = container.publicCloudDatabase
		privateDB = container.privateCloudDatabase
	    
	}
	
	func saveUser(user : CKRecord ) -> Void{
		
		publicDB.save(user) {
		    (record, error) in
		    if let error = error {
		        // Insert error handling
                print("error setting up record \(error)")
		        return
		    }
		    // Insert successfully saved record code
		}
	}
	
	func queryUser(access_email : String ) -> Void{
		
		let predicate = NSPredicate(format: "access_email == %@", access_email)
        let query = CKQuery(recordType: "User", predicate: predicate)
		
	    publicDB.perform(query, inZoneWith: nil) { (results, error) in
	      
	      if (error != nil) {
		        // Error handling for failed fetch from public database
		        print("error fetching record \(String(describing: error))")
		    }
		    else {
		        // Display the fetched records
		        
		    }
	    }	
	}
	
	func deleteUser(recordID : CKRecordID ) -> Void{
		
		publicDB.delete(withRecordID: recordID, completionHandler: {(recordID, error) in
            
            if error == nil {
                
                //Record deleted   
            }
        })
	}
	
}
