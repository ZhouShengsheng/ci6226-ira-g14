# Search Engine & IR Applications

**Description**: Assignment for CI6226 Information Retrieval & Analysis at NTU in Singapore.\
**Group**: 14\
**Members**: Ke Xiangyu, Li Jinjin, Li Xihan, Zhou Shengsheng\
**Project Home**: https://github.com/ZhouShengsheng/ci6226-ira-g14

## Abstract

Information retrieval plays a huge role in our every day life. We would always like to search for information that we care about or want to know using search engines like Google, Baidu, Bing and so on. And there are always new friends, movies or products that applications we use like Twitter, Youtube or eBay recommends to us. These services are all based on information retrieval. In a certain point, human beings are hard to live without information retrieval in an information era.
Stack Overflow is a Q&A website which allows programmers to search, ask, answer and make comments on questions. And our project aims to provide a search engine system to let user quickly search for their questions based on part of the Stack Overflow data. We also built another two applications to analysis the data. One application is to retrieve the popularity of each programming language from 2008 to 2016. The other application is to rank users based on their answer counts.

## 1. Introduction

### 1.1 System Architecture

<img src="https://github.com/ZhouShengsheng/ci6226-ira-g14/blob/master/images/image1_architecture.png" alt="System Architecture" width="800">

### 1.2 Project Structure

<img src="https://github.com/ZhouShengsheng/ci6226-ira-g14/blob/master/images/image2_project_structure.png" alt="Project Structure" width="360">

### 1.3 Fontend Web Pages

- **Home Page**

<img src="https://github.com/ZhouShengsheng/ci6226-ira-g14/blob/master/images/image3_home_page.png" alt="Home Page" width="600">

- **Search Engine**

<img src="https://github.com/ZhouShengsheng/ci6226-ira-g14/blob/master/images/image4_search_engine.png" alt="Search Engine" width="600">

- **Language Trend**

<img src="https://github.com/ZhouShengsheng/ci6226-ira-g14/blob/master/images/image5_language_trend.png" alt="Language Trend" width="600">

- **Answering User Ranking**

<img src="https://github.com/ZhouShengsheng/ci6226-ira-g14/blob/master/images/image6_user_ranking.png" alt="Answering User Ranking" width="600">


## 2. Installation

### 2.1 Installation with Eclipse

#### (1) Import

Open Eclipse, choose **[import -> Maven -> Existing Maven Projects]**. Select the Root Directory as ci6226-ira-g14 and select all projects in the window. Click finish to finish the import.

#### (2) Change configuration

You can change the application configuration in the **src/main/resources/application.yml** file. For instance, you can change port, Posts.xml path and index path. Please refer to specific .yml file for more details of the configuration parameters.

#### (3) Run

Every project has the same manner to run. Let's take search engine for example:
Right click the Main.java in package ci6226.ira.g14.search.engine and select **[Run As -> Spring Boot App]**. Then the search engine will run and listen on 9001 port. The following list lists all of the Main classes and default listened ports of the projects:

| Application   | Main.java Package | Port  |
| ------------- |:-------------:| -----:|
| Search Engine | ci6226.ira.g14.search.engine.Main.java | 9001 |
| Language Trend| ci6226.ira.g14.app.language.trend.Main.java | 9002 |
| Answering User Ranking      | ci6226.ira.g14.app.answering.user.ranking.Main.java | 9003 |
| Frontend      | ci6226.ira.g14.fe.Main.java | 8000 |

### 2.2 Installation with IntelliJ

#### (1) Import

Open IntelliJ, in the welcome page, select **[Create Project -> Empty Project -> Next -> Navigate location to ci6226-ira-g14 -> Finish]**.\
Then, in the project structure window, add all projects as module by select **[ '+' Symbol -> Import Module -> Choose g14-search-engine (or others) -> Open -> Import module from external module -> Maven -> Next ... -> Finish]**.

#### (2) Change configuration

Same as described in the section 2.1.(2).

#### (3) Run

Right click the Main.java and select Run 'Main'. For detailed Main.java packages and ports, please refer to section 2.1.(3).

### 2.3 Installation with Terminal

#### (1) Install Maven

If you don't have maven installed in your system, please refer to the Maven installation guide to get the Maven installed. The installation guide is at https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html.

#### (2) Change configuration

Same as described in the section 2.1.(2).

#### (3) Run

Let's take search engine for example:\
Enter the root directory of the search engine project:\
`$ cd /path/to/g14-search-engine`\
Run the application:\
`$ mvn spring-boot:run`\
And the search engine application will run and listen on 9001 port. Other applications share the same procedure to run in a terminal.

## 3. Usage

### 3.1 Frontend

#### (1) Search Engine Page
You can use web browser to visit **http://localhost:8000** to access the main web page of the frontend. The search input box is right in the center of the web page. You can input search keywords and select which field you want to search. Then click the search button and the search results will be rendered. If you want to another search, you can simply input keywords in the top left input box instead going back the main page.

#### (2) Language Trend Page
The url is **http://localhost:8000/languages**. Select start year and end year and click the search button. The language trend results will be rendered in a graph.

#### (3) Answering User Ranking Page
The url is **http://localhost:8000/users**. Input a top number users in the top left box and click search button. The results will be rendered in the below list.

### 3.2 Search Engine

If you want to interact with the search engine without the web page, you can. The search engine exposes an API from which the web page fetches data actually.

- Request
```
GET http://localhost:9000/api/search?field=all&keywords=python+convert+string+to+int&count=2
```

- Parameter
```
field: title, body or all
keywords: query keywords (url encoded)
count: top N results
```

- Response
```json
[
  {
    "title": "converting string to int python",
    "body": "<p>how can I convert a string to an int in python\nsay I have this array</p>\n\n<pre><code>['(111,11,12)','(12,34,56)'] to [(111,11,12),(12,34,56)]\n</code></pre>\n\n<p>Any help will be appreciated thanks</p>\n",
    "docId": 582335,
    "score": 30.801956
  },
  {
    "title": "Python: Recursively convert int to binary string",
    "body": "<p>I am trying to recursively convert int to binary string but I don't really understand how the whole positive int to binary string conversion works.</p>\n\n<p>Also found out that apparently each position is like a representation of the power of 2.</p>\n\n<p>Any explanation as to how to convert a positive int to a string representation as shown in the Googled example above is extremely helpful.</p>\n",
    "docId": 608825,
    "score": 28.072052
  }
]
```


### 3.3 Language Trend

- Request
```
GET http://localhost:9001/api/language_trend?rankLanguages=java%2Cc%2Cpython%2Cphp&startYear=2015&endYear=2016
```

- Parameter
```
rankLanguages: programming languages that you want to get trend (url encoded)
startYear: star year
endYear: end year
```

- Response
```json
{
  "2015": [
    {
      "name": "java",
      "popularity": 50467
    },
    {
      "name": "c",
      "popularity": 35010
    },
    {
      "name": "python",
      "popularity": 59565
    },
    {
      "name": "php",
      "popularity": 58975
    }
  ],
  "2016": [
    {
      "name": "java",
      "popularity": 49137
    },
    {
      "name": "c",
      "popularity": 34022
    },
    {
      "name": "python",
      "popularity": 70951
    },
    {
      "name": "php",
      "popularity": 59549
    }
  ]
}
```


### 3.4 Answering User Ranking

- Request
```
GET http://localhost:9002/api/user_ranking?userCount=3
```

- Parameter
```
userCount: top N users
```

- Response
```json
[
  {
    "userID": "22656",
    "username": "Jon Skeet",
    "anwseredCount": 33477
  },
  {
    "userID": "1144035",
    "username": null,
    "anwseredCount": 31656
  },
  {
    "userID": "29407",
    "username": "Darin",
    "anwseredCount": 21217
  }
]
```

## 4. Deployment

We provided shell scripts to easily deploy the applications to remote server. Note that these scripts can only be used in a UNIX-like system like UNIX, Linux, Mac OS and so on. They cannot be used in Windows.

#### (1) Server Configuration

In order to use the script for deployment, you need to configure your remote server to allow login via ssh key. If your server is not configured in such manner, please refer to this tutorial for guide on how to configure: https://www.digitalocean.com/community/tutorials/how-to-configure-ssh-key-based-authentication-on-a-linux-server.

And you also need to configure the firewall to allow TCP port used in the applications.

#### (2) Scripts Modification

The scripts are in the root directory of every project. For example, in the search engine project, the scripts are:
- g14-search-engine/deploy.sh
- g14-search-engine/server.sh

Next step is to modify the deploy.sh to set the user and server to yours:
```bash
REMOTE_SERVER=your_server_address
REMOTE_USER=your_server_user
```

And modify the server.sh to configure the java environment:
```bash
export JAVA_HOME=/path/to/jdk
```

#### (3) One Key Deployment

Finally, you can run the deploy.sh to automatically build and deploy the project to the remote server:
```bash
$ ./deploy.sh
```
