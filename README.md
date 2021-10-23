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

* User can login/create an account to login to the app
* User can link bank account(s) to app
* Utilize bank APIs for account information
* Weekly/monthly analysis (charts and graphs) of spending and budgeting of all accounts
* Record different kinds of currency
* log in password to protect the security

**Optional Nice-to-have Stories**

* Link news articles concerning the market and other finance related topics. 
* Have an education section with quick links to useful YouTube videos.

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

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Accounts Screen
* Spending Analysis Screen
* Settings Screen

**Flow Navigation** (Screen to Screen)

* Log In Screen
   * => Accounts Screen
 
* Registration Screen
   * => Accounts Screen
 
* Accounts Screen
   * => Individual Account Screen
   * => Settings Screen
   * => Spending Analysis Screen
   
* Settings Screen
   * => Accounts Screen

* Spending Analysis Screen
    * => Accounts Screen

* Individual Account Screen
    * => None

## Wireframes
<img src=images/basic_wireframe.png width=800>

### [BONUS] Digital Wireframes & Mockups
<img src=https://user-images.githubusercontent.com/58927442/138538318-a14999b2-f647-4134-95fd-817ad14f47ca.png width=250><img src=https://user-images.githubusercontent.com/58927442/138540359-b104569d-e7a3-46d6-bef6-2e063804579b.png width=250><img src=https://user-images.githubusercontent.com/58927442/138538351-0cb36f25-20c8-4a8e-9f99-1fbbc0d110d5.png width=250><img src=https://user-images.githubusercontent.com/58927442/138538360-8f869e00-a5ab-466c-92e9-75d262948f96.png width=250><img src=https://user-images.githubusercontent.com/58927442/138538365-2a92cfb9-8f51-47ec-9adc-31314d6509b5.png width=250><img src=https://user-images.githubusercontent.com/58927442/138540736-ba419806-f1a5-4fc2-9cf0-b138187fd819.png width=250><img src=https://user-images.githubusercontent.com/58927442/138538386-88283847-1c53-4368-8e00-f734ce76ef30.png width=250><img src=https://user-images.githubusercontent.com/58927442/138538409-b8ef166e-cf32-4703-b84a-8a49950e5005.png width=250><img src=https://user-images.githubusercontent.com/58927442/138538417-5dc7b2b2-bf14-410d-a75d-c8de9897056a.png width=250>

### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]
### Models
[Add table of models]
### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
