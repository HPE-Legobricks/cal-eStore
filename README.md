# RFI CDT-ADPQ-0117

Our working prototype is located at:

[Working prototype](http://13.64.248.119:8080/calestore/loginform)

# Technical Approach

## Problem Statement

For our prototype, Enterprise Services needed to
develop an application that allows California state employees to shop online
for hardware, software, and related services. Users need to be able to place,
cancel, track, and analyze their orders online. Additionally,
authorized administrative users need to be able to publish product and service
information, as well as track, analyze and view order data. 

## Prototype - Definition, Goal, and Tasks

Our prototype, which we named Cal eStore, serves as
an end to end application catering to authorized users and administrative
users. The prototype features sample computing hardware, software, and related
services from pre-established state contracts and allows users to compare and
buy products, as well as track and review their orders. The prototype permits
administrative users to publish and update hardware, software, and service
information on the platform, along with the capabilities to analyze and view
order data.

## Approach

Agile methodology served as the foundation for the
prototype development. The Agile
philosophy revolves around the concept of adding value to business as fast as
possible in a Volatile Uncertain Complex Ambiguous (VUCA) environment. We
followed scrum based agile methodology for the entire process. One of the key
differentiators of Enterprise Services distributed agile delivery model is the
Demand Team and Demand Planning. Major highlights involved in the entire
process are as follows:

**Creation of a cross-functional Demand Team:** Our
cross functional team was made up of groups of people with different functional
expertise working toward a common goal. Cross functionality in scrum means a
team composed of specialized generalists—individuals with breadth in a number
of areas and disciplines. 

The Demand Team consists of the following:

* Product manager—delivery manager

* Product expert—proxy product manager

* Business analyst 

* Technical architect

* Test lead—interaction designer/user
researcher/usability tester

The Demand Team members spend 25-30% of their time
shortlisting features for the next sprint, elaborating the product backlog, and
preparing the sprint backlog for the next sprint. This model not only helps in
early resolution of dependencies, approvals, and impediments, but also enables
a 3-in-a-box model (product manager, product expert, and test lead) that ensures
continuous collaboration. The Demand Team works toward definition of clear user
stories with acceptance criteria, resolving dependencies and/or impediments,
seeking approvals, etc. The
Demand Team works with business owners and architects to understand enough
about the requirements to enable creation of storyboards and high level design
(HLD) documents; these form the basis of Knowledge Transfer (KT) for scrum
delivery teams. Prior to a sprint kick off, all candidate themes and stories
are presented to the team. The product expert focuses on analysis tasks while
the technical architect produces the HLD.

**Roles of the Demand Team:** The roles of the
demand team include the following:

* Assist the larger team with definition of qualification criteria

* Produce an HLD and impact assessment to feed into storyboards and Sprint scope planning during the Demand Funnel phase 

* Define minimum criteria for entry to the product backlog

**Demand Planning:** The Cal eStore prototype
development spanned three sprints in the scrum methodology. Each sprint was
carefully compressed from a three week cycle to a crisp 5 days per sprint; thus
adopting a time boxed sprint cycle of one week. The following are the highlights of each sprint:

* **Poker Method for Estimating:** During each sprint, the scrum team began with a sprint planning
meeting where the sprint backlog is story pointed using the **planning poker method—a consensus based
estimating technique.** This technique helps in estimating the relative
effort or size of user stories. In planning poker, members of the scrum team,
except the scrum master, make estimates by picking cards that are numbered
based on Fibonacci sequence. Planning poker forces members to think
autonomously and put forward their own estimates simultaneously. People who
have proposed high estimates and low estimates are asked to give
justification for their estimation. The estimation is repeated until a
consensus is reached on the estimation of story points. 

* **Life cycle management tool (JIRA):** Scrum teams commit to sprint backlog features
based on their capacity and velocity. The Agile lifecycle management tool,
JIRA, is updated with the planning information. 

* **Meetings:**
During the course of the sprint, **daily
stand-up meetings** are used to track progress and provide continuous
feedback. When the work package is ready, the team demonstrates the working
software to the business stakeholders for review and acceptance. **Retrospective meetings** are conducted
at the end of every sprint to enable continuous improvement and agile maturity.
At the end of each sprint the team meets to reflect on progress and identify
possible improvements. This retrospective supports team formation and bonding,
and may aid on conflict identification and resolution. Ultimately, the
retrospective helps build the team's sense of ownership and self-management. 

Agile delivery typically releases functionality
into production at the end of each sprint. It is also normal for organizations
to expect a few features to be released into production together to optimize
release planning and management activities. Our Agile development processes
support all of these aforementioned methods and were used for the Cal eStore
prototype. 

##User Centric Design

The success of any human end user application is
based on how well the application is designed to allow users to navigate
through features in a simple, intuitive way. We wanted the users to have a
pleasant journey even though they may have no prior experience using a web
application for purchasing electronics goods and services for their office. We
also wanted the application to be intuitive without the need for providing
a user training session on how to use the application. When we decided to
develop Cal eStore, we knew user centric design was applicable to an online
store for purchasing electronic goods and services. 

We included prospective users throughout the design
process and made sure their feedback was incorporated into each iteration of
the prototype.

The UX Design team was responsible for employing
user centric design, which includes conducting research for the user
experience, persona development, Simple UX, and usability tests for wireframes
to translate the product features into product designs. The UX design team
consists of the visual designer, business analyst, front end web developer, and
writer/content designer.

Following are the user-centric design techniques
used for developing the application.

* Conducted interviews

* Built personas

* Created user stories

* Developed wireframes

* Performed role-play

* Created mock-ups

* Performed usability testing

## Extract, Transform, and Load (ETL) process 

In the [ETL process] (./Contributing Documents/Technical Approach ETL Process.pdf), we extracted data from
the resource.pdf file which was provided in the RFI, loaded it in to the staging
database and, based on visual scans and data profiling reviews, we developed
custom business rules to transform the data. The transformed and cleaned data
was loaded into the final fact and dimension tables.

For example, we
decided to break the “Item Description” in to multiple columns like product
name, operating system, processor, memory, storage, warranty, connectivity
type, HDD RPM, and RAM type so that we can enable the product comparison
feature in the Cal eStore.

##Architecture 

High level [architecture](./Contributing Documents/Technical Approach High Level Architecture.pdf) pertains to the architecture that would be used
for developing the prototype. The architecture outlines the main components and
their interfaces. 

**Modular Design:** The team modularized (a design
approach which subdivides a system into smaller parts known as module) the framework
so that the product could be assembled at a later point of time. The approach
used was to unglue the dependency of user interface, business logic, and
backend services. This approach vision gives the team more flexibility and also
the ability to work independently without any negotiable dependency on others.

**User Interface:** For the User Interface, a small
screen translation was designed along with a larger desktop and tablet screen
size. The philosophy of responsiveness guided us to the use of ascendable,
scalable and extendable elements. Instead of solely using back-end networks,
front-end networks like Bootstrap, HTML5, CSS3 and jQuery were used to support
the necessary responsiveness of the user interface.

**Server End:** SpringMVC and Hibernate were used
to access data models from the server end. Controllers were designed in a way
to be ported to any data service available and can be easily replaced with a
data access model and web services.

**Database:** MySQL relational database was used to
load the prototype’s data source.

##DevOps Automation 

Continuous integration, delivery, and deployment is
key to automating the delivery process. In order to successfully implement the
concept of agile and continuous delivery it is highly important that
collaboration and communication exist between various work groups. [DevOps](./Contributing Documents/Technical Approach DevOps Automation.pdf) is a set of processes
and methods for this communication and collaboration of both software and
information technology while automating the processes of software delivery and
infrastructure changes.

The primary goal of any business is Time to Market
(TTM). The challenge that lies ahead of the business is to move faster in a
rapidly evolving market without compromising the quality of our product.
Implementation of DevOps Automation is the answer to this. DevOps Automation
employs the modus operandi to establish a process chain between development,
deployment, testing and monitoring teams. It helps streamline the entire
process efficiently. 

**Continuous Integration (CI):** CI is used to communicate issues, such a potential bugs related to
software, to the development team. The development team in turn acts quickly to
resolve the issues associated to the software before it gets promoted to a higher
environment. By using this method production failures can be reduced. Hence
quality gateways are set in the CI pipeline, where builds will fail if software
code does not meet actual quality standards. For example, bugs and
vulnerabilities are categorized as Blocker, Critical, Major, Minor and Info. We
set the quality gateway so that no “Blocker” or “Critical” issues are
acceptable for promotion into higher environments. Failing code in the early stages is superior to failing later in the production
stages. Additionally, security tools are introduced so that a strong wall
against security attacks like SQL injections and cross-site scripting (XSS) is
established. HPE Fortify is used for security scans to identify the vulnerabilities
in the code. Fortify, identifies the piece of code where a potential
vulnerability may occur, and provides the solution to fix the security issue.

**Continuous Deliver / Deployment (CD):** CD is leveraged so that quick
releases were accomplished with incremental builds using test automation. We
achieved high quality with CD tools which were able to create virtual machines
(VMs) in a cloud on a demand basis. We selected configuration management tools
to satisfy stated requirements and for functional and performance testing.
Testing automation ensured that success and failure notifications are sent to
the respective teams.

**Continuous Monitoring (CM):** One of the main
requirements for any business activity is providing high availability to
applications, so businesses leverage continuous monitoring of applications,
system performance and network, and apply self-healing techniques with custom
scripts. We used SiteScope for monitoring the Cal eStore application at the
system level. The tool continuously monitors the available application and
services, and restarts the services on the VM(s) if it fails. 

##Summary

This Technical Approach has described our process
and methodology in creating the prototype. We had 27 functional user stories
identified across 5 Epics (Profile, Catalog, Checkout, Manage Product Inventory,
and Reports) during the Sprint 0 back log grooming session. Sixteen user
stories were prioritized by the Product Manager and executed by the agile development
team in three sprints. There are 11 user stories remaining in the backlog after
end of Sprint 3. The documentation regarding the user stories is provided in
the [JIRA presentation](./Contributing Documents/D.4b_JIRA tool example.pdf). We used our Global Agile Software
Delivery Model to guide our project teams’ delivery, using a set of
interrelated roles, practices, and work products that optimized the flow of
business value delivery. Our project was a success, delivering on each goal we
set for our prototype. Our prototype meets the needs of the prospective users
and administrators. 

 
#US Digital Services Playbook

We complied with the US Digital Service: Digital
Services Playbook in creation of our prototype. See the [Service Plays Table](./Contributing Documents/J.1_US Digital Services Playbook.pdf) for details about our approach.

 
#RFI Requirements (a) through (t)
## Team

### (a) Leadership

Derek was selected as
the product owner with authority and responsibility for providing the business
case. Derek was ultimately accountable for delivery of the working prototype.
Derek has over 20 years of Information Technology experience in software
product development, implementation and operations management. This includes
five years of Agile methodology experience in various team roles.

For this project,
Derek oversaw the overall product prototype vision and acted as direct liaison
to system users to gather feedback, conduct conceptual, wireframe and mock-up
interview sessions. Derek interpreted the data and information gathered to
create a product definition and epic stories. Additionally, he worked closely
with the Scrum Master, Business Analyst and Technical Architect to organize and
prioritize the prototype feature backlog.

### (b) Collaboration team

We assembled a multidisciplinary,
collaborative virtual team that included the following members in the roles
indicated:

* Derek - Product Manager - Final authority representing the customer's interest

* Vasu - Agile Coach - Coached and mentored the team in Agile best practices

* Lavanya - Scrum Master - Facilitator for the team and product owner

* Satish - Technical Architect - Overall application design

* Dinesh - Business Analyst - Designed, built, tested, or ran the application

* Ashvin - Visual Designer - Designed, built, tested, or ran the application

* Nihar and Suresh - Front End Web Developers - Designed, built, tested, or ran the application

* Suman - Backend Web Developer - Designed, built, tested, or ran the application

* Utsav - DevOps Engineer - Designed, built, tested, or ran the application

* Amit and Lata - Quality Assurance Analysts - Ensured quality throughout the project

* Sandy - Technical Writer/Content Designer - Developed the strategy and execution of content
 
## Design and Development

### (c) Understanding users 

Utilizing both the US Digital Service: Digital
Services Playbook: Play 1: Understanding What People Need as well as
Enterprise Services Design methodology, user needs were explored, captured, and
documented early in the design and development process. The team quickly
identified:

* Different user roles

* Needs of those users within the context of the service

* Ways the service will meet identified needs

Additionally, interviews were conducted using role-play
to further enhance the understanding and needs of users. The Product Manager,
Derek, conducted the interviews to ask about administrator preferences in
design and implementation, and user preferences in the shopping experience. The
interviews were conducted from a script that followed the Digital Services
Playbook. The interviewees gave input that was incorporated into the final
prototype. For example, the following quotes were made by interviewees and
incorporated into the prototype:

* Make it simple to publish the new products.

* I am unfamiliar with online ordering systems, so
make it simple to establish my tasks.

* I need a simple dashboard that gives me relevant
insights on the user orders. 

* I order a lot of personal items from Amazon
within minutes. I want the same experience for my official equipment ordering
system.

More detail about our [understanding of users](./Contributing Documents/C.1_User Centric Design.pdf) is attached.

### (d) User-centric design tools and/or techniques 

Early in the development of
the prototype, our team employed the following user centric design methods
which are applicable and proven to be valid in use case scenarios. The
following discussions elaborate on the elements of these seven user centric
design techniques. 

**Research**

The product manager and team
researched current tool features, identified pros and cons, and applied what was learned
to the prototype design. The team also collaborated with the product team at HP
Inc.—associates of Enterprise Services—to shape the content of the prototype,
including how the products should be displayed, the specific product
specifications needed by the users, and similar items.

The product design team
started the discovery process by exploring the resources available online, as
well as reaching out to our existing commercial e-commerce platform development
teams, to learn more about the actual facts that need to be addressed during
the prototype development.

The team also evaluated several
public e-commerce sites to draw an understanding of the existing landscape in
the online shopping community. Sites evaluated include the following:

* Dell shopping http://www.dell.com/en-us

* Amazon shopping https://www.amazon.com/

* eBay shopping http://www.ebay.com/ 

The design team was also
inspired by the Enterprise Services Employees internal shopping website. This
site allows authorized Enterprise Services employees access to buy products. An
administrator controls the product publishing process. 

**Interview the user**

This step involved
preparing a questionnaire based on the Digital Services
Playbook: Play 2: Address the Whole Experience, from Start to Finish and Play
3: Make it Simple and Intuitive. The interviews were held individually with
a system user as well as a system administrator both of which were from outside
the development team. 

**Build the persona** 

In the development process
we based our prototype on the following fictional persona information.

California state user
persona: Florinda Sanchez

Florinda has worked for the
California Department of Education for over 9 years and is currently assigned
the role of office manager for a team of 23 professional staff. The duties of
office manager include timely ordering of desktop computer equipment and
software for newly hired staff to ensure new employees are productive upon
hiring. Florinda has many additional duties and responsibilities and therefore
ease of ordering and order status tracking are very important to her and her
team.

California state admin
persona: Emma Mercier

Emma has worked for the
State of California Procurement Division for 12 year and is currently
responsible for overseeing all IT services purchased by several state agencies.
Emma must ensure state agencies use existing state contracts for purchasing IT
services and also helps to evaluate user feedback on services delivered to help
negotiate future vendor contracts. Emma wants to ensure state users are getting
the services they need within the confines of the existing contracts as well as
making sure the state is getting the best prices possible with the various
services vendors.

**Create user stories** 

Based on requirements for the prototype and insight
from potential users gathered via interviews, we created user stories to guide
our development. This information was then refined and prioritized in a product
backlog grooming session with the product owner. We have attached the 
[user stories](./Contributing Documents/D.4a_User Stories.pdf) and documentation of the
[JIRA tool](./Contributing Documents/D.4b_JIRA tool example.pdf) where we capture the user stories. 

**Develop wireframes** 

As the starting point to
represent the prototypes on layman’s terms, we developed wireframes. The team
created the wireframes based on the user stories. The [initial user wireframes](./Contributing Documents/D.5a_User Initial Wireframe.pdf) and [initial administrator wireframes](./Contributing Documents/D.5b_Admin Initial Wireframe.pdf) are attached. 

**Perform role-play** 

We identified potential users from our team. Member 1
acted as an authorized user and Member 2 acted as the authorized administrator
for the prototype. After creating preliminary wireframes, we did an interactive
show-and-tell process for each user type where we collected feedback and
challenges. The following points highlight the important feedback gathered from
the users:

* Quick intelligence search option for departments while user is creating their profile

* Sign off option accessible from all screens of the application

* During the checkout, an option to utilize the user’s default address located on the user’s profile  

* Ability to use the home page slider for non-promotional info for user, new product published, new vendor added, new category added, and important messages from the admin user

* Remove the item Availability Status from the Shopping Cart as "we should assume all the products for the user are available for purchase."

Availability might be a feature for admin user

* At Checkout, show the Payment option as under the Department Contract “XXXX-XXXX-0123.” For security reasons the contract id will not be visible to users. 

Based on the feedback, the wireframes were updated as needed. The final [user](./Contributing Documents/D.6b_User Final Wireframe.pdf) and [admin](./Contributing Documents/D.6a_Admin Final Wireframe.pdf) wireframes are
attached.

**Create mock-ups** 

Using the wireframes and
user input, we created mock-ups that follow the US Web Design Standards.
Following these standards allows the user to have a pleasant experience when utilizing
the application. Mock-ups are based on a simple
and flexible design style guide. The mock-ups were used in the usability
testing for the Visual Design.

### (e) GitHub code commits

GitHub was used as our source code management tool
for daily code commits. We also used GitHub for storing documents related to
the prototype.

### (f) RESTful API 

We used Swagger to document the Cal eStore RESTful
API. Our link to Swagger API for Cal eStore is: http://13.64.248.119:8080/calestore/swagger-ui.html.
Further detail of our [Swagger](./Contributing Documents/F.2_Swagger Specification.pdf) and [code flow updates](./Contributing Documents/F.3_Swagger documentation.pdf) are attached. 

### (g) ADA Section 508 and WCAG 2.0 compliance 

To comply with WCAG, our team used HTML5 and CSS3
technology during front end development. Once built, the code was tested
against the W3 standard site. This methodology supports compliance with Section
508 of the Americans with Disabilities Act. See our [WCAG and W3 Compliance Tables](./Contributing Documents/G.1_ADA WCAG Compliance.pdf) for details.

### (h) Design style guide and pattern library 

We used the US Web Design Standards UI component
library and strictly followed brand guidelines to define color, typography,
form fields, spacing and click-throughs. Use of U.S. Web Design Standards
further ensured that selected standardize color palettes are visually
appealing. See our [Design Document](./Contributing Documents/H.1_Design Style Guide and Pattern Library.pdf) for details of how we have implemented these standards.

### (i) Usability testing

While designing our prototype, we invited several prospective users to
role play to review wireframes and provide feedback. We considered Usability
Testing as a critical component of the application development to identify gaps
and additional items we needed to address for user needs. Specifically, we
tried to understand whether users thought the flow of the application and
initial interactions were intuitive. Our approach was to provide the wireframe
to a user who was not involved in the design process and capture his or her
thought process while going through the application wireframes. We also created
a list of pre-defined questions to ask the users. More detail of our Usability
Testing related to the [wireframe](./Contributing Documents/I.2_Usability Testing Wireframes.pdf)
is attached.

While designing our prototype, we invited prospective users to role play
and provide feedback on visual designs by interacting with HTML mock-ups. Our
design team and business analyst conducted a face-to-face interview with the
users and asked many questions to simulate possible tasks the user would
perform when using the real application. To ensure unbiased feedback, the users
interviewed were unfamiliar with the project requirements for the prototype.
More detail of our Usability Testing related to [Visual Design](./Contributing Documents/I.1_Usability Testing Visual Design.pdf) is attached.

### (j) Iterative Approach

We used an iterative design approach following the
Enterprise Services LLC (ES) Global Agile Software Delivery Model. We have
developed and fine-tuned the Global Agile Software Delivery Model to guide our
project teams’ delivery of projects using Agile delivery models. At its core
our global Agile software delivery model is based on the Agile manifesto,
principles, and the scrum delivery model. We have attached our [Agile Delivery Model](./Contributing Documents/A.1_Agile Scrum Process.pdf).

### (k) Multiple Device and Responsive Design

Our design includes small-screen translation, in
addition to larger desktop and tablet screen sizes as shown in [Screen Sizes example](./Contributing Documents/K.1_Screen Size Examples.pdf). 

A responsive philosophy directed our use of
scalable and extendable elements. To support this the team used frontend
frameworks such as Bootstrap, HTML5, CSS3 and jQuery; proven aids for personal
and professional responsive design frameworks.

We used a GRID based layout to ensure consistency,
balance, rhythm, and order. GRID is an important tool in user experience design
and provides a level of familiarity which enables users to intuitively navigate
through a site or application in a way which feels natural.

## Technology

### (l) Open-Source Usage

We used the following open-source technologies in
creating the Cal eStore application. The list includes product names and
versions.

* HTML5 - Used for structuring and presenting content on the World Wide Web

* CSS3 - Used for describing the presentation of a document written in a markup language

* MySQL DB - Used for web application development

* jQuery - Used to ease the client-side scripting of HTML

* Bootstrap - Used to design templates for typography, forms, buttons, navigation and other interface components

* Spring MVC - Used for layered architecture to select component you want while others are ignored and it’s both comprehensive and modular

* Hibernate - Used for mapping an object-oriented domain model to a relational database

* Ajax - Used for exchanging data with a server and updating parts of a web page without reloading the entire page

* Jenkins (2.32.1) - Used for continuous integration and continuous delivery of projects

* SonarQube (6.2) - Used for continuous inspection of code quality

* Selenium - Used to perform functional test cases on prototype

* JMeter - Used to test performance both on static and dynamic resources

* Nexus (3.2.0) - Used to store the prototype artifacts

### (m) IaaS and PaaS

Our team deployed the prototype on an
Infrastructure as a Platform as a Service (PaaS) provider. The prototype is
deployed on PaaS – Azure Cloud Infrastructure created by Microsoft for
building, deploying, and managing applications and services through a global
network of Microsoft-managed data centers.

## Testing

### (n) Automated unit tests

Automated unit testing is a method of testing
software in which units (small sections) of code are rigorously checked to
ensure they work correctly. The goal of automated unit testing is to
demonstrate that each individual part of a larger software development project
works as intended.

Automated unit tests may also aid in documenting the software's functionality.

The results of a unit test help gain insight into
how the code interfaces with the systems on which they run. Developers may also
see how the code interacts with the end users who provide it with input and
other units that are part of the same software package.

We used JUnit (Java Unit test) to develop test cases and JaCoCo (Java
Code Coverage) to automate test cases in continuous integration.

As part of the Cal eStore application quality check and validation process we have performed
automated functional testing. Selenium was the primary tool of choice for implementing Test Automation. We have created [testing scenarios](./Contributing Documents/N.3_Test Cases.pdf) that will help to create automated testing. The detailed information is [attached.](./Contributing Documents/N.1_Automated Testing.pdf)

We have also performed performance testing for the
application by simulating the surges in traffic to identify performance bottlenecks.
We have used the jMeter Graphical Server performance testing tool which is an
Open Source testing software. More detail about the [performance testing](./Contributing Documents/N.4_Performance Testing.pdf) is attached. This testing was conducted on a minimum capacity server. 


### (o) Automated integration testing for IaaS or PaaS code deployment 

We used the following tools to automate integration
testing:

* Jenkins to orchestrate continuous integration

* GitHub to retrieve source code

* JaCoCo to run the JUnit test cases

* SonarQube to perform static code analysis

* HP Fortify to complete vulnerability scans

* Nexus to store the prototype artifact

* Selenium to perform functional test cases on prototype

Details of our automated [integration testing](./Contributing Documents/Technical Approach DevOps Automation.pdf) are
attached.

## Environment and Installation

### (p) Configuration management

For configuration management, we used Ansible. We
also wrote playbooks to do incremental software releases to Dev, Staging, and
Production environments. We used Jmeter for performance testing. A notification
mechanism was also set to alert select team members of system failures. 
The details of our [configuration management](./Contributing Documents/Technical Approach DevOps Automation.pdf)
approach are attached.
 
### (q) Continuous monitoring

We used SiteScope to monitor application and system
performance. Custom scripts were written based on certain condition for
“self-healing” within the services. A notification mechanism was also set to
alert select team members of system failures. 
Details of our [continuous monitoring](./Contributing Documents/Technical Approach DevOps Automation.pdf)
are attached.

### (r) Container based deployment

Our team setup and used container based deployment.
The CI process generates a .war file and Jenkins uploads it into Nexus, which
acts as a central repository for the artifacts. For deployment, Ansible is
called to download the latest .war file from Nexus to build a docker image and
upload that image to a docker registry—in this case Nexus. In the final step, Ansible
retrieves the Docker image from Nexus, deploys it to the target nodes, starts
the container, and keeps all of the environments consistent. See [DevOps Automation](./Contributing Documents/Technical Approach DevOps Automation.pdf) for more details.

### (s) Installation guide

The following is our installation guide for our Cal eStore prototype:

* Install Git: apt-get install git

* Install Java: apt-get install oracle-java8-installer

* Install Maven: apt-get install maven

* Install Tomcat: apt-get install tomcat7

* Install MySQL: apt-get install mysql-server

* Get source code: git clone https://github.com/EnterpriseServices/cal-eStore.git

* Build project: mvn package (to be run at root of cloned project)

* Run DB scripts: https://github.com/EnterpriseServices/cal-eStore/tree/master/Documentation/DBScripts 

* Deploy the code: cp \target\caleStore.war \tomcatpath\webapps\. (Copy the caleStore.war file into Tomcat webapps folder)

### (t) Prototype and underlying platforms

The packages and platforms used to build and run the prototype are open-sources.

 

