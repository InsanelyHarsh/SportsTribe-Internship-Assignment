import SwiftUI
import shared

struct ContentView: View {
    
	let greet = Greeting().greet()
    @StateObject var vm:ViewModel = ViewModel()

	var body: some View {
        NavigationView{
            VStack(spacing: 60){
                
                VStack(spacing: 5){
                    vm.userImage
                        .resizable()
                        .frame(width: 150, height: 150, alignment: .center)
                        .onTapGesture {
                            vm.showingImagePicker.toggle()
                        }
                        .clipShape(Circle())
                    
                    Button("Remove") {
                        withAnimation(.default){
                            vm.removeUserImage()
                        }
                    }
                    .opacity(vm.selectedImage != nil ? 1 : 0)
                }
                
                VStack{
                    CustomField(text: $vm.userName, emptyField: "Enter Your Name", fieldName: "Name", keyBoardType: .default) {
                        
                    }
                    
                    
                    CustomField(text: $vm.userEmail, emptyField: "Enter Your Email", fieldName: "Email", keyBoardType: .default) {
                        
                    }
                    
                    
                    CustomButton(isPrimary: true, buttonTitle: "Save Record") {
                        vm.saveData()
                    }
                    .padding(.top,20)
                }
                
                Spacer()
            }
            
            .sheet(isPresented: $vm.showingImagePicker) {
                ImagePicker(image: $vm.selectedImage)
            }
            .alert(isPresented: $vm.showAlert, content: {
                vm.alert
            })
            
            .onChange(of: vm.selectedImage) { _ in vm.loadImage() }
            .navigationTitle("Safe Vault")
            .navigationBarTitleDisplayMode(.inline)
        }
        
	}
    
//    func loadImage() {
//        guard let inputImage = inputImage else { return }
//        userImage = Image(uiImage: inputImage)
//    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
