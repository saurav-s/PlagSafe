# PlagSafe
### by team-109

#### Members:
Ding Jin</br>
Rohit Raj</br>
Sanket Saurav</br>
Tridiv Nandi</br>


1. Link to the live System<br />
[Plagsafe - A plagiarism detector by team 109](https://plagsafe.herokuapp.com)<br />
[Jenkins](https://plagsafe.herokuapp.com/) (username: team109 password: maximum123)<br />

2. Links to YouTube videos<br />
[System Demo](https://youtu.be/Fxu8Ci1um20)<br />
[System Setup](https://youtu.be/xu1INQPGJAc)<br />
[Final Presentation](https://youtu.be/1WfJTs-QnyE)<br />

3. Project Overview<br/>
Plagsafe is an online plagiarism detection tool which targets source code written in python to detect plagiarism in them.<br/>
It is deployed on AWS, link provided above, and provides the user to check plagiarised content for the following types of plagiarism:<br/>
* Renaming detection
* Refactoring detection
* Logical similarity detection
* Weighted combination

4. System Setup<br/>
* Clone git repo
`git clone https://github.ccs.neu.edu/cs5500/team-109.git`

* How to install and run the application locally?

	* From terminal<br/>
	  Note: Maven should be installed. [How to install Maven](https://maven.apache.org/install.html) <br/>
      Test if maven is installed using the command `mvn -version` <br/>

	* Install the application:<br/>
      Go to `{local project folder path}/phaseC/plagsafe/`. 
      For example: <br/>
      `cd ~/Documents/Git/team-109/phaseC/plagsafe/`<br/>

      	* Make sure pom.xml is present in this location<br/>
      	* maven install:<br/>
        `mvn clean install`</br>

      	* Run tests<br/>
      	`mvn test`<br/>

      	* Run the application:<br/>
      	Go to `target` folder in `{local project folder path}/phaseC/plagsafe/` which will contain the `war` file generated.<br/>

      	For example:<br/>
      	`cd ~/Documents/Git/team-109/phaseC/plagsafe/target`<br/>

      	Make sure a war file named `plagsafe-team109-0.0.1-SNAPSHOT.war` is present at this location.<br/>

      	* Finally, run the application using the command: <br/>

      	`java -jar plagsafe-team109-0.0.1-SNAPSHOT.war`<br/>

		* If somehting like this comes up, the application is running successfully. <br/>

		![Application Running](https://github.ccs.neu.edu/cs5500/team-109/blob/candidate/images/ApplicationRunSuccess.png "Application Running")<br/>

		* Open a web browser and go to `localhost:8080`<br/>

		![localhost URL](https://github.ccs.neu.edu/cs5500/team-109/blob/candidate/images/localhostURL.png "Local host url")<br/>

5. Support of Future Development<br/>
* push the changes and generate a pull request
* This will trigger the Continuous Integration build on Jenkins(Link provided above) automatically
* When the builds are successfully passing the admins will approve the pull request
* After the pull request is approved, the changes can be merged. 
* This will trigger build for the master branch on Jenkins automatically
* On success of master's build Deploy job on Jenkins will deploy the application on the AWS (Link provided above)




