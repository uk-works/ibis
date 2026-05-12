package com.integratedbookinfo.system.store;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.integratedbookinfo.system.auth.AuthMapper;
import com.integratedbookinfo.system.auth.dto.LoginUserRequestDTO;
import com.integratedbookinfo.system.store.dto.AccountDTO;
import com.integratedbookinfo.system.store.dto.StoreCreateRequestDTO;
import com.integratedbookinfo.system.store.dto.StoreDTO;
import com.integratedbookinfo.system.store.dto.StoreEditRequestDTO;
import com.integratedbookinfo.system.store.dto.StoreListResponseDTO;

import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreServiceImpl implements StoreService {

	private final StoreMapper storeMapper;
	private final AuthMapper authMapper;
	
	@Override
	public StoreListResponseDTO getStoreList(int page, int numPerPage, String keyword) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	@Override
	public int getTotalPages(int numPerPage, String keyword) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
	
	@Override
	public StoreEditRequestDTO getStoreEdit(String storeId) {
		
		StoreEditRequestDTO storeEdit = storeMapper.selectStoreEditByStoreId(storeId);
		
		storeEdit.getAccount().setPassword("");
		
		return storeEdit;
	}
	
	@Override
	public void saveStore(StoreCreateRequestDTO storeCreateRequest) {
		AccountDTO account = storeCreateRequest.getAccount();
		StoreDTO store = storeCreateRequest.getStore();
		
		String recentStoreId = storeMapper.selectLastStoreIdForUpdate();
		
		store.setStoreId(generateStoreId(recentStoreId));;
		
		String hashedPassword = BCrypt.hashpw(account.getPassword(), BCrypt.gensalt());

		// 平文パスワードを暗号化パスワードに上書き
		account.setPassword(hashedPassword);
		
		storeMapper.insertStore(store);
		
		authMapper.insertAccount(account);
		
		// 書店アカウントテーブル登録
		storeMapper.insertStoreAccounts(store.getStoreId(), account.getAccountId());
	}
	
	@Override
	public void modifyStore(StoreEditRequestDTO storeEditRequest, LoginUserRequestDTO loginUser) {
		
		StoreDTO store = storeEditRequest.getStore();
		AccountDTO account = storeEditRequest.getAccount();
		
		if(account.getPassword() == null || account.getPassword().isEmpty()) {
			StoreEditRequestDTO originStoreEdit = storeMapper.selectStoreEditByStoreId(loginUser.getStoreId());
			
			System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(originStoreEdit));
			account.setPassword(originStoreEdit.getAccount().getPassword());

		} else {
			String hashedPassword = BCrypt.hashpw(account.getPassword(), BCrypt.gensalt());
			account.setPassword(hashedPassword);
		}

		storeMapper.updateAccount(account, loginUser.getStoreId());
		storeMapper.updateStore(store, loginUser.getStoreId());
	}
	
	@Override
	public void removeStore(String storeId) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	public String generateStoreId(String recentStoreId) {
		String numberPart = recentStoreId.substring(5);
		int next = Integer.parseInt(numberPart) + 1;

		if (next > 9999) {
			throw new RuntimeException("storeID上限に到達");
		}

		return String.format("store%04d", next);
	}
}
