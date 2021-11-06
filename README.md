# Banknote

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
This is a mobile app that tracks a user's various bank accounts from multiple banks and makes financial calculations based on the information found in those accounts. Such calculations could be related to spending, budgeting, investing, etc.

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Finance
- **Mobile:** Since accounts must be updated on the regular for proper and correct financial operations/calculations to be done, the app will have real-time updating. Push notifications would be utilized to give the user information after updates.
- **Story:** There is a functional value in this app related to giving users beneficial financial information. For someone who would like to be more financially responsible or literate, this app would fill that role.
- **Market:** Any one with multiple bank accounts and is interested in recording and/or analyzing their spending would be in the potential market for this app. This app would provide great value for people who travel often or have multiple income sources, for example.
- **Habit:** Frequency: whenever they spend or on a daily basis to check spending habits. Usage: just consuming.
- **Scope:** It will be fairly technically challenging based on the fact that multiple APIs for multiple banks must be utilized and security and authentication must be maintained for all of these accounts at all times. Furthermore, complex mathematical functions might be created to provide the user with the financial information required.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* - [x] User can login to an account in the app
* - [x] User can register an account within the app
* - [ ] User can link bank account(s) to app
* - [ ] Weekly/monthly analysis (charts and graphs) of spending and budgeting of all accounts
* - [ ] Record different kinds of currency
* - [ ] User can change their password 

**Optional Nice-to-have Stories**

* - [ ] Link news articles concerning the market and other finance related topics. 
* - [ ] Have an education section with quick links to useful YouTube videos.
* - [ ] Utilize bank APIs for account information

### 2. Screen Archetypes

* Login Screen
   * User logs into the app

* Registration Screen
   * User can create a new account

* Accounts Screen
    * User can select an account to view

* Individual Account Screen
    * User can view their recent transactions from their account

* Spending Analysis Screen
    * User can view weekly/monthly spending summaries

* Settings Screen
    * User can view and change settings

* News Screen
    * User can scroll through financially relevant news

* Add a new Account Screen
    * User can link a new bank account to view in Accounts Screen

* Bank Selection Screen
    * User can see a list of the most popular banks in the US.

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Accounts Screen
* Spending Analysis Screen
* Settings Screen
* News Screen (Optional)

**Flow Navigation** (Screen to Screen)

* Log In Screen
   * => Accounts Screen
   * => Registration Screen
 
* Registration Screen
   * => Accounts Screen
   * => Log In Screen
 
* Accounts Screen
   * => Individual Account Screen
   * => Settings Screen
   * => Spending Analysis Screen
   * => News Screen (Optional)
   
* Settings Screen
   * => Accounts Screen
   * => Add a new Account Screen

* Spending Analysis Screen
   * => Accounts Screen

* Individual Account Screen
   * => Accounts Screen

* Add a new Account Screen
   * => Settings Screen
   * => Bank Selection Screen

* Bank Selection Screen
    * => Add a new Account Screen

## Wireframes
<img src="https://i.imgur.com/MD3Hl2S.png" width=800>

### [BONUS] Digital Wireframes & Mockups
<img src=https://user-images.githubusercontent.com/58927442/138538318-a14999b2-f647-4134-95fd-817ad14f47ca.png width=250><img src=https://user-images.githubusercontent.com/58927442/138540359-b104569d-e7a3-46d6-bef6-2e063804579b.png width=250><img src=https://user-images.githubusercontent.com/66866365/138610252-30ee2577-dac5-4e43-a2d4-3fccd824b93a.png width=250>
<img src=https://user-images.githubusercontent.com/58927442/138538360-8f869e00-a5ab-466c-92e9-75d262948f96.png width=250><img src=https://user-images.githubusercontent.com/66866365/138610293-94bc53dc-505b-4432-a218-4453c9f6d831.png width=250><img src=https://user-images.githubusercontent.com/58927442/138540736-ba419806-f1a5-4fc2-9cf0-b138187fd819.png width=250>
<img src=https://user-images.githubusercontent.com/66866365/138610315-3b9a3f75-eae3-4c0d-ae95-b535c8589dff.png width=250><img src=https://user-images.githubusercontent.com/58927442/138538409-b8ef166e-cf32-4703-b84a-8a49950e5005.png width=250><img src=https://user-images.githubusercontent.com/58927442/138538417-5dc7b2b2-bf14-410d-a75d-c8de9897056a.png width=250>
<img src=https://user-images.githubusercontent.com/66866365/138610188-fffa77ee-8f16-4fbf-915e-311455f0b080.png width=250>


### [BONUS] Interactive Prototype

## Schema 
### Models
#### User
   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | username      | String   | Name of user|
   | password      | String   | password    |
   | push notifs   | Boolean  | allows push |
   
#### Account
   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | user          | Pointer to User | User associated with acct |
   | bank          | String   | Name of bank |
   | account number| Number   | Account number|
   | account password| String | Account password|
   | balance       | Number   | Balance of account |
   | transactions  | [Pointer to Transaction] | Transactions associated with account |


#### Transaction 
   | Property      | Type     | Description         |
   | ------------- | -------- | ------------------- |
   | account       | Pointer to Account | Account associated with transaction |
   | date          | DateTime | date of transaction |
   | balance       | Number   | dollar amount of transaction|
   | isSpending    | Boolean  | is transaction positive/negative |
   
#### Article 
   | Property      | Type     | Description         |
   | ------------- | -------- | ------------------- |
   | headline      | String   | headline of news article |
   | content       | String   | content of news article  |
   | category      | String   | category of news article |
   | creation date | DateTime | creation date of news article |

#### Summary
   | Property      | Type     | Description |
   | ------------- | -------- | ----------- |
   | date          | DateTime | date of summary |
   | spending      | [Pointer to Account] | total of all spending |
   | income        | [Pointer to Account] | total of all income |
    
#### Analysis 
   | Property      | Type     | Description |
   | ------------- | -------- | ----------- | 
   | summaries     | [Pointer to Summary]| summaries for analysis |
   | type          | Number   | 0 = day, 1 = weekly, 2 = monthly |
   
### Networking
#### Registration Screen
   | CRUD      | HTTP Verb     | Example |
   | ------------- | -------- | ----------- | 
   | Create     | POST | create user |


```Java
private void registerUser(String username, String password) {
	ParseUser user = new ParseUser();
	user.setUsername(username);
	user.setPassword(password);
	user.signUpInBackground(new SignUpCallback() {
		@Override
		public void done(ParseException e) {
			if (e != null) {
				// Toast : "Failed to Register your credentials"
				Log.e(TAG, "Issue with signing up", e);
				return;
			}
				// Toast : "Welcome!"
			    // go to main activity
		}
	});
}
``` 

#### Accounts Screen
   | CRUD      | HTTP Verb     | Example |
   | ------------- | -------- | ----------- | 
   | Read     | GET | get all accounts |


```Java
protected void queryAccounts() {
	ParseQuery<Account> query = ParseQuery.getQuery(Account.class);
	query.findInBackground(new FindCallback<Post>() {
		@Override
		public void done(List<Account> accounts, ParseException e) {
			if (e != null) {
				Log.e(TAG, "Issue with getting accounts", e);
				return;
			}
			// allAccounts is input for an adapter
			allAccounts.addAll(account);
			// adapter in onViewCreated() for AccountsFragment.java
			adapter.notifyDataSetChanged();
		}
	});
}
``` 

#### Add a New Account Screen
   | CRUD      | HTTP Verb     | Example |
   | ------------- | -------- | ----------- | 
   | Create     | POST | create account |


```Java
protected void postAccount(ParseUser currentUser, String name, Long accountNumber, String password, Long balance) {
	Account account = new Account();
	account.setUser(currentUser);
	account.setBankName(name);
	account.setAccountNumber(accountNumber);
	account.setAccountPassword(password);
	account.setAccountBalance(balance);
	account.saveInBackground(new SaveCallback() {
		@Override
		public void done(ParseException e) {
			if (e != null) {
				Log.e(TAG, "Error while saving", e);
				// Toast: "Could not add account."
			}
			Log.i(TAG, "Post save was successful!");
			// Maybe clear out text views
		}
	});
}
``` 
   
#### Individual Account Screen
   | CRUD      | HTTP Verb     | Example |
   | ------------- | -------- | ----------- | 
   | Read       | GET  | get information on an account |
   | Delete     | DELETE | delete account     |
   | Create     | POST   | create transaction |
   | Read       | GET    | get transaction |
   | Update     | PUT  | update transaction |
   | Delete     | DELETE | delete transaction |
   

```Java
/* Getting information on an account */ 
// subject to change
protected void queryAccount() {
	ParseQuery<Account> query = ParseQuery.getQuery("accountName");
	query.getInBackground("accountId", new GetCallback<Account>() {
		public void done(Account account, ParseException e) {
			if (e != null) {
			  // something went wrong 
			} 
			// Do the magic here
		}
	});
}

/* Deleting an account */ 
account.deleteInBackground();
   
/* Creating a transaction */ 
protected void postTransaction(Account account, String date, Long balance, boolean isSpending) {
	Transaction transaction = new Transaction();
	account.setAccount(account);
	account.setDate(date);
	account.setBalance(balance);
	account.setSpending(isSpending);
	account.saveInBackground(new SaveCallback() {
		@Override
		public void done(ParseException e) {
			if (e != null) {
				Log.e(TAG, "Error while saving", e);
				// Toast: "Could not add transaction."
			}
			Log.i(TAG, "Save was successful!");
			// Maybe clear out text views
		}
	});
}

/* Getting a transaction */ 
// subject to change
protected void queryAccount() {
	ParseQuery<Transaction> query = ParseQuery.getQuery("transactionName");
	query.getInBackground("transactionId", new GetCallback<Transaction>() {
		public void done(Transaction transaction, ParseException e) {
			if (e != null) {
			  // something went wrong 
			} 
			// Do the magic here
		}
	});
}

/* Updating a transaction */ 
// subject to change
ParseQuery<Transaction> query = ParseQuery.getQuery("transactionName");
// Retrieve the object by id
query.getInBackground("xWMyZ4YEGZ (objectId)", new GetCallback<Transaction>() {
	public void done(Transaction transaction, ParseException e) {
		if (e != null) {
			// something went wrong	
		}
	// Now let's update it with some new data. In this case, only balance and isSpending
	// will get sent to your Parse Server. date hasn't changed.
	transaction.put("balance", 100.00);
	transaction.put("isSpending", false);
	transaction.saveInBackground();
	}
});


/* Deleting a transaction */ 
transaction.deleteInBackground();

```

#### Spending Analysis Screen
   | CRUD      | HTTP Verb     | Example |
   | ------------- | -------- | ----------- | 
   | Read     | GET | get analysis |
   | Update     | PUT | update analysis |

```Java
/* Getting an analysis */ 
// subject to change
ParseQuery<Analysis> query = ParseQuery.getQuery("analysis");
query.getInBackground("analysisID", new GetCallback<Analysis>() {
	public void done(Analysis analysis, ParseException e) {
		if (e != null) {
		  // something went wrong 
		} 
		// Do the magic here
	}
});

/* Updating an analysis */ 
// subject to change
ParseQuery<Analysis> query = ParseQuery.getQuery("analysis");
// Retrieve the object by id
query.getInBackground("xWMyZ4YEGZ (objectId)", new GetCallback<Analysis>() {
	public void done(Analysis analysis, ParseException e) {
		if (e != null) {
			// something went wrong	
		}
	analysis.put("type", 2);
	analysis.saveInBackground();
	}
});
```
   
#### Settings Screen
   | CRUD      | HTTP Verb     | Example |
   | ------------- | -------- | ----------- | 
   | Update     | PUT | update user push preference |

```Java
/* Updating Settings */ 
// subject to change
ParseQuery<Setting> query = ParseQuery.getQuery("setting");
// Retrieve the object by id
query.getInBackground("xWMyZ4YEGZ (objectId)", new GetCallback<Setting>() {
	public void done(Setting setting, ParseException e) {
		if (e != null) {
			// something went wrong	
		}
	setting.put("push_preference", true);
	setting.saveInBackground();
	}
});
```

#### Optional News Screen
   | CRUD      | HTTP Verb     | Example |
   | ------------- | -------- | ----------- | 
   | Create     | POST | create news articles (from API) |
   | Read     | GET | get all news articles |
   | Update     | PUT | update news article |
   | Delete     | DELETE | delete news article(s) |

- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]

### Build Progress

#### Registration and Login Screens

<img src='https://i.imgur.com/nq80RfO.gif' title='Registration and login screen walkthrough' height='480' alt='Registration and login screen walkthrough' />
