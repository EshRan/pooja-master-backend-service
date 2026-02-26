## for running if already running

sudo docker-compose down
sudo docker-compose up -d --build


## or

sudo docker build -t ubuntu_app .
sudo docker stop pooja-backend-app
sudo docker rm pooja-backend-app
sudo docker run -d -p 8080:8080 --name pooja-backend-app ubuntu_app