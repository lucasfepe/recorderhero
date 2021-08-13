package com.appsdeveloperblog.keycloak;

import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.credential.UserCredentialStore;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RoleModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.models.utils.KeycloakModelUtils;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.adapter.AbstractUserAdapter;
import org.keycloak.storage.user.UserLookupProvider;


import java.nio.file.AccessDeniedException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RemoteUserStorageProvider implements UserStorageProvider,
UserLookupProvider, CredentialInputValidator {
	private KeycloakSession session; 
	private ComponentModel model;
	private UsersApiService usersService;
	
	public RemoteUserStorageProvider(KeycloakSession session, 
			ComponentModel model, UsersApiService usersService) {
		this.session = session;
		this.model = model;
		this.usersService = usersService;
	}


	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserModel getUserById(String id, RealmModel realm) {
		StorageId storageId = new StorageId(id);
		String username = storageId.getExternalId();

		return getUserByUsername(username, realm);
	}



	@Override
	public UserModel getUserByUsername(String username, RealmModel realm) {

		UserModel userModel = null;
		System.out.println("USERNAMEEEEE@@@@@@@@@@ " + username);
		User user = usersService.getUserDetails(username);
		System.out.println("USER@@@@@@@@@@ " + user);
		if (user != null) {
			userModel = createUserModel(username, realm);
		}
		if (user.getRoles() != null && !user.getRoles().isEmpty()) {
			for (Role authority : user.getRoles()) {
				RoleModel roleModel = realm.getRole(authority.getRole());
				if (roleModel != null) {
					userModel.grantRole(roleModel);
				}
			}
		}
		return userModel;
//				new UserAdapter(session, realm, model, user);

	}





	private UserModel createUserModel(String username, RealmModel realm) {
		return new AbstractUserAdapter(session, realm, model) {
			@Override
			public String getUsername() {
				return username;
			}

			@Override
			public Set<RoleModel> getRoleMappings() {

				Set<RoleModel> set = new HashSet<>();
				Set<RoleModel> roles = realm.getRoles();
				Iterator iter = roles.iterator();

				while (iter.hasNext()) {
					RoleModel role = (RoleModel) iter.next();
					set.add(role);
				}

				return set;
			}
		};
	}

	@Override
	public UserModel getUserByEmail(String email, RealmModel realm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supportsCredentialType(String credentialType) {
		 
		return PasswordCredentialModel.TYPE.equals(credentialType);
	}

    @Override
    public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
        if (!supportsCredentialType(credentialType)) {
            return false;
        }
        return !getCredentialStore().getStoredCredentialsByType(realm, user, credentialType).isEmpty();
    }
    
    private UserCredentialStore getCredentialStore() {
        return session.userCredentialManager();
    }

	@Override
	public boolean isValid(RealmModel realm, UserModel user, CredentialInput credentialInput) {
		System.out.println("USERNAMEEEEE@@@@@@@@@@2 " + user.getUsername());
		VerifyPasswordResponse verifyPasswordResponse = usersService.verifyUserPassword(user.getUsername(), 
				credentialInput.getChallengeResponse());
		
		if(verifyPasswordResponse == null) return false;
				

		return verifyPasswordResponse.getResult();
	}

}
