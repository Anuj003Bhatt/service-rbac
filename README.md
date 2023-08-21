# service-rbac
This repository contains implementation for RBAC: Role based access control

## Description
This repository contains a working model for a customer RBAC (Role based access control) solution.
Using this repository one can achieve the following:

### Users
- Create a User (Encryption using a random generated salt is also in place)
- Authenticate a user by username and password
  - This will return a JWT containing basic assignment information that can be used for authorization 
- Create user groups and add multiple users to those groups
- List users in the system (Paginated)
- Find users, user groups by their IDs
- Find users by username
- Disable an active user
- Enable an in-active user

### Roles
- Create a role
- Create a role groups and add roles to these groups
- Get role or role group by their repective IDs
- List roles (paginated)
- Search roles by keywords (Keywords are searched across role names only for now)

### Permission
These are the acutal authorities/permissions granted to the users
- Create a permission identiyfing some access in your organization
- Get permission by ID or name
- List all permission
- List all permission for certain access types. Eg: Create/Read/Update/Delete]

### Assignments
This resource takes care of the assignment listed below:
- Add permissions to roles
- Assign `roles` to `users`.
- Assign `roles` to `role groups`.
- Add `users` to `user groups`.
- Assign `roles` to `user groups`.
- Assign `role groups` to `users`.
  

## Swagger
After setting up the system locally use the below swagger URL to play around with the repository
URL: http://localhost:8002/swagger-ui/index.html

## Setup

### Pre-requisites
Before setting up the system one must have below things installed on their system:
- Java (preferred latest version)
- Docker (For setting up dependencies like Postgres/PgAdmin)

### Steps
- Step 1: Clone the repository
- Step 2: Open terminal and navigate to the repository's root
- Step 3: Run the docker command in the terminal `docker-compose up`
- Step 4: Create DB with below steps
    - Connect to the database via `pgAdmin`, follow below steps:
        - Open browser and navigate to `localhost:5050`
        - Login using below credentials: (coming from docker-compose.yaml)
            - Username/email: `admin@admin.com`
            - Password: `root`
        - Register a server with a preferred name and below details for db: (again coming from the docker-compose.yaml)
            - Host: `postgres_database`
            - Maintenance DB: `postgres`
            - User: `postgres`
            - Password: `root`
            - Port: `5432`
        - Once the server is registered create a datbase with the name of `rbac`
- Step 5: Run the service


## API Snapshots from Postman:
<img width="1342" alt="image" src="https://github.com/Anuj003Bhatt/service-rbac/assets/34508608/d63dc56a-5029-4827-ae3a-2a2e6ff6f84e">

## Postman collection
For ease of working with the repo a postman collection has been added [here](https://github.com/Anuj003Bhatt/service-rbac/blob/main/docs/RBAC.postman_collection.json)
