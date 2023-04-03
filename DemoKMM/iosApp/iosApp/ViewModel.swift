//
//  ViewModel.swift
//  iosApp
//
//  Created by Harsh Yadav on 03/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI


class ViewModel:ObservableObject{
    
    @Published var userName:String = ""
    @Published var userEmail:String = ""
    
    @Published private(set) var userImage:Image = Image(systemName: "person.circle")
    @Published var showingImagePicker:Bool = false
    
    @Published var selectedImage: UIImage?
    
    @Published var showAlert:Bool = false
    @Published private(set) var alert:Alert = Alert(title: Text("Default Alert!"))
    private let pref = KMMPreference(context: NSObject())
    
    init(){
        getUserData()
    }
    
    func loadImage() {
        guard let selectedImage = selectedImage else { return }
        userImage = Image(uiImage: selectedImage)
    }
    
    func removeUserImage(){
        self.selectedImage = nil
        self.userImage = Image(systemName: "person.circle")
    }
    
    
    func saveData(){
        
        let validator = Validator()
        
        if(validator.isValidUserName(name: userName) && validator.isValidEmail(email: userEmail)){
            //save
            saveUserData(key: Constants.userNameKey, value: userName)
            saveUserData(key: Constants.userEmailKey, value: userEmail)
            
            guard  selectedImage != nil else { return }
            let imageDataString = convertImageToBase64(image: selectedImage!)
            
            saveUserData(key: Constants.userImageKey, value: imageDataString)
            
            presentAlert(title: "Saved", message: "Your Data is saved successfully")
        }else{
            //show alert
            presentAlert(title: "Invalid Credentails", message: "Enter Valid Name & Email")
        }
    }
    
    
    private func presentAlert(title:String,message:String = ""){
        self.alert = Alert(title: Text(title), message: Text(message))
        self.showAlert = true
    }
    
    func convertImageToBase64(image: UIImage) -> String {
        let imageData = UIImage.pngData(image)()!
        return imageData.base64EncodedString(options:   Data.Base64EncodingOptions.lineLength64Characters)
    }
}


extension ViewModel{
    
    
    private func getUserData(){
        
        let savedUserName = pref.getString(key: Constants.userNameKey)
        let savedUserEmail = pref.getString(key: Constants.userEmailKey)
        let savedImage = pref.getString(key: Constants.userImageKey)
        
        if(savedUserName != nil){
            self.userName = savedUserName!
        }
        if(savedUserEmail != nil){
            self.userEmail = savedUserEmail!
        }
//        self.userName = savedUserName != nil ? savedUserName! : "-"
//        self.userEmail = savedUserEmail != nil ? savedUserEmail! : "-"
        
        if let savedImage = savedImage{

            if(savedImage.toImage() != nil){
                selectedImage = savedImage.toImage()!
                userImage = Image(uiImage: selectedImage!)
            }

        }
    }
    
    
    private func saveUserData(key:String,value:String){
        pref.put(key: key, value: value)
    }
}
struct Constants{
    static let userNameKey:String = "USER_NAME_IOS"
    static let userEmailKey:String = "USER_EMAIL_IOS"
    
    static let userImageKey:String = "USER_PROFILE_IOS"
}

extension String{
    func toImage() -> UIImage? {
            if let data = Data(base64Encoded: self, options: .ignoreUnknownCharacters){
                return UIImage(data: data)
            }
            return nil
        }
}
