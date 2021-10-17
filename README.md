# What In The Wallet

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
[Add picture of your hand sketched wireframes in this section]
<img src="YOUR_WIREFRAME_IMAGE_URL" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]
### Models
[Add table of models]
### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
