
# VIDEO BLOG CORE SERVICE

Microservice for video blog, it is a simple CRUD service for video blog, where you could fetch all the video post created for all users, and also create, delete, update videos and reviews.

For more technical details, please refer to the API documentation:

http://localhost:9091/swagger-ui


## **Instalation:**

The easiest way to run this project is using docker, so you need to have docker and docker installed in your machine.

Steps:
1. Clone the project
2. Modified the application.properties file with your database credentials, and the database name.
3. Package the project with the following command: mvn clean package
4. Run the following command in the root of the project: docker build -t video-blog-core-service .
5. Run the following command in the root of the project: docker run -p 9091:9091 video-blog-core-service
6. The service will be running in the port 9091, you can check the API documentation in the following URL: http://localhost:9091/swagger-ui




Copyright (c) 2022, Jose Eduardo Zuñiga Santillan
All rights reserved.
Visit my website: https://eduzsantillan-dev.vercel.app/
Visit my LinkedIn: https://www.linkedin.com/in/eduzuniga/