package com.appsdeveloperblog.keycloak;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.credential.UserCredentialStore;
import org.keycloak.models.*;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.models.utils.KeycloakModelUtils;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.adapter.AbstractUserAdapter;
import org.keycloak.storage.adapter.AbstractUserAdapterFederatedStorage;
import org.keycloak.storage.user.UserLookupProvider;
import org.keycloak.storage.user.UserQueryProvider;


import java.nio.file.AccessDeniedException;
import java.util.*;

public class RemoteUserStorageProvider implements UserStorageProvider,
UserLookupProvider, CredentialInputValidator, UserQueryProvider {
	private KeycloakSession session; 
	private ComponentModel model;
	private UsersApiService usersService;
	
	public RemoteUserStorageProvider(KeycloakSession session, 
			ComponentModel model, UsersApiService usersService) {
		System.out.println("@@@@@29");
		this.session = session;
		this.model = model;
		this.usersService = usersService;
	}


	@Override
	public void close() {
		System.out.println("@@@@@28");
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserModel getUserById(String id, RealmModel realm) {
		System.out.println("@@@@@27");
		StorageId storageId = new StorageId(id);
		String username = storageId.getExternalId();

		return getUserByUsername(username, realm);
	}



	@Override
	public UserModel getUserByUsername(String username, RealmModel realm) {
		System.out.println("@@@@@26");
		UserModel userModel = null;

		User user = usersService.getUserDetails(username);

		if (user != null) {
			userModel = createUserModel(username, realm);
		}
//		if (user.getRoles() != null && !user.getRoles().isEmpty()) {
//			for (Role authority : user.getRoles()) {
//				RoleModel roleModel = realm.getRole(authority.getRole());
//				if (roleModel != null) {
//					userModel.grantRole(roleModel);
//				}
//			}
//		}
		return userModel;
//				new UserAdapter(session, realm, model, user);

	}





	private UserModel createUserModel(String username, RealmModel realm ) {
		System.out.println("@@@@@25");
		return new AbstractUserAdapterFederatedStorage(session, realm, model) {

			@Override
			public String getUsername() {
				return username;
			}

			@Override
			public void setUsername(String s) {

			}

//			@Override
//			public Set<RoleModel> getRoleMappings() {
//
//				Set<RoleModel> set = new HashSet<>();
//				Set<RoleModel> roles = realm.getRoles();
//				Iterator iter = roles.iterator();
//
//				while (iter.hasNext()) {
//					RoleModel role = (RoleModel) iter.next();
//					System.out.println("Role: " + role);
//					set.add(role);
//				}
//
//				return set;
//			}
		};
	}

	@Override
	public UserModel getUserByEmail(String email, RealmModel realm) {
		System.out.println("@@@@@24");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supportsCredentialType(String credentialType) {
		System.out.println("@@@@@23");
		return PasswordCredentialModel.TYPE.equals(credentialType);
	}

    @Override
    public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
		System.out.println("@@@@@22");
        if (!supportsCredentialType(credentialType)) {
            return false;
        }
        return !getCredentialStore().getStoredCredentialsByType(realm, user, credentialType).isEmpty();
    }
    
    private UserCredentialStore getCredentialStore() {
		System.out.println("@@@@@21");
        return session.userCredentialManager();
    }

	@Override
	public boolean isValid(RealmModel realm, UserModel user, CredentialInput credentialInput) {
		System.out.println("@@@@@20");
		VerifyPasswordResponse verifyPasswordResponse = usersService.verifyUserPassword(user.getUsername(), 
				credentialInput.getChallengeResponse());
		
		if(verifyPasswordResponse == null) return false;
				

		return verifyPasswordResponse.getResult();
	}

	@Override
	public int getUsersCount(RealmModel realmModel) {
		System.out.println("@@@@@19");
		return 0;
	}

	@Override
	public List<UserModel> getUsers(RealmModel realmModel) {
		System.out.println("@@@@@18");
		return null;
	}

	@Override
	public List<UserModel> getUsers(RealmModel realmModel, int i, int i1) {
		System.out.println("@@@@@17");
		return null;
	}

	@Override
	public List<UserModel> searchForUser(String s, RealmModel realmModel) {
		System.out.println("@@@@@16");
		return null;
	}

	@Override
	public List<UserModel> searchForUser(String s, RealmModel realmModel, int i, int i1) {
		List<UserModel> userModels = new ArrayList<>();
		UserModel currentUser = null;
		System.out.println("@@@@@15");
		List<User> users = usersService.getUsers();
		for (User user: users
			 ) {
			userModels.add(createUserModel(user.getUserName(), realmModel));
			for (Role authority : user.getRoles()) {
				RoleModel roleModel = realmModel.getRole(authority.getRole());
				if (roleModel != null) {
					currentUser.grantRole(roleModel);
				}

			}
			userModels.add(currentUser);
		}

		return userModels;
	}

	@Override
	public List<UserModel> searchForUser(Map<String, String> map, RealmModel realmModel) {
		System.out.println("@@@@@14");
		List<UserModel> userModels = new ArrayList<>();


		List<User> users = usersService.getUsers();

		for (User user: users
		) {
			UserModel currentUser = createUserModel(user.getEmail(), realmModel);
			userModels.add(currentUser);






			if (user.getRoles() != null && !user.getRoles().isEmpty()) {
			for (Role authority : user.getRoles()) {

				RoleModel roleModel = realmModel.getRole(authority.getRole());
				if (roleModel != null) {
					currentUser.grantRole(roleModel);
				}

			}}

		}

		return userModels;
	}

	@Override
	public List<UserModel> searchForUser(Map<String, String> map, RealmModel realmModel, int i, int i1) {
		System.out.println("@@@@@13");
		return null;
	}

	@Override
	public List<UserModel> getGroupMembers(RealmModel realmModel, GroupModel groupModel, int i, int i1) {
		System.out.println("@@@@@12");
		return null;
	}

	@Override
	public List<UserModel> getGroupMembers(RealmModel realmModel, GroupModel groupModel) {
		System.out.println("@@@@@11");
		return null;
	}

	@Override
	public List<UserModel> searchForUserByUserAttribute(String s, String s1, RealmModel realmModel) {
		System.out.println("@@@@@10");
		return null;
	}
}
