# lilicurroad

A Jenkins plugin that will look up a Yum repo's database, and populate a Jenkins parameter dropdown. 

The primary use case for this plugin is the configuration of deployable artifact versions when running Ansible.


### Package 

```bash
mvn hpi:hpi
```


### Run

```bash
mvn hpi:run -Djetty.port=9090 -Pjenkins
```

... or ...

```bash
export MAVEN_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=5005,suspend=n" 
mvn hpi:run -Djetty.port=9090 -Pjenkins
```


### Build


... then ...

```bash
open http://localhost:9090/jenkins
```


... or ...

[Click Here](http://localhost:9090/jenkins)


### Usage

#### Step 1 - Configure the global repositories

Navigate to the main menu...

![](src/doc/images/Step_01_MainMenu.png "Main Menu")

Select "Manage Jenkins" followed by "Configure System" then add one or more Yum repositories...

![](src/doc/images/Step_02_RepositoryConfiguration.png "Repository Configuration")

#### Step 2 - Configure the project

Navigate to the main menu...

![](src/doc/images/Step_01_MainMenu.png "Main Menu")

Select "New Item" to create a new project...

![](src/doc/images/Step_03_CreateProject.png "Create Project")

Check the box to indicate that the build is parameterised...

![](src/doc/images/Step_04_AddBuildParameter.png "Add Build Paramter")

Select the "Package version parameter"...

![](src/doc/images/Step_05_AddPackageVersionParameter.png "Add Package Version Paramter")

Give the parameter a name, select the repository and package...

![](src/doc/images/Step_06_ConfigurePackageVersionParameter.png "Configure Package Version Paramter")

Add a build step to print the build environment parameters... 

![](src/doc/images/Step_07_ConfigureBuild.png "Configure Build")

#### Step 3 - Build the project

Navigate to the job menu...

![](src/doc/images/Step_08_ReturnToJobMenu.png "Job Menu")

Trigger a parameterised build by selecting a version of the specified package...


![](src/doc/images/Step_09_TriggerJobWithVersion.png "Trigger Job With Version")

#### Step 4 - Check the project build logs

View the build output...

![](src/doc/images/Step_10_CompletedBuild.png "Completed Job")
![](src/doc/images/Step_11_BuildOutputContainingEnvironment.png "Console Output")

