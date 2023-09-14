cd server
docker-compose up --build middleware -d
cd ../frontend
docker-compose up --build -d
cd ..
echo
echo "=========================== middleware logs ==========================="
echo
docker logs middleware --follow
