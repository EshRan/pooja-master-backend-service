## for running if already running

sudo docker-compose down
sudo docker-compose up -d --build


## or

sudo docker build -t ubuntu_app .
sudo docker stop pooja-backend-app
sudo docker rm pooja-backend-app
sudo docker run -d -p 8080:8080 --name pooja-backend-app ubuntu_app


postgres backup

PostgreSQL Daily Backup Setup
Overview
This project uses PostgreSQL running inside Docker.
To prevent data loss, a daily automated backup is configured.
Backup runs daily at 2:00 AM
Backup file is overwritten daily
Only the latest backup is kept
Backup is compressed (.sql.gz)
🔹 Backup Directory
Backups are stored at:
/home/ubuntu/postgres-backups/
Main backup file:
mydb_backup.sql.gz
🔹 Backup Script
Location:
/home/ubuntu/postgres-backups/backup.sh
Script content:
#!/bin/bash

BACKUP_DIR="/home/ubuntu/postgres-backups"
BACKUP_FILE="$BACKUP_DIR/mydb_backup.sql.gz"

# Create compressed backup and overwrite existing file
docker exec local-postgres pg_dump -U myuser mydb | gzip > $BACKUP_FILE
Make executable:
chmod +x /home/ubuntu/postgres-backups/backup.sh
🔹 Manual Backup
To trigger backup manually:
/home/ubuntu/postgres-backups/backup.sh
🔹 Cron Job Configuration
Open crontab:
crontab -e
Add:
0 2 * * * /home/ubuntu/postgres-backups/backup.sh >> /home/ubuntu/postgres-backups/backup.log 2>&1
This runs backup every day at 2:00 AM.
🔹 Restore Database From Backup
If database needs to be restored:
gunzip -c /home/ubuntu/postgres-backups/mydb_backup.sql.gz | docker exec -i local-postgres psql -U myuser mydb
🔹 Important Notes
docker-compose down → Safe (data preserved)
docker-compose down -v → ⚠️ Deletes database volume
Deleting EC2 instance → ⚠️ Backup and data lost
Consider uploading backup to S3 for production safety






