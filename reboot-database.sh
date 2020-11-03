if [[ "$OSTYPE" == "linux-gnu" ]]; then
    sudo -s psql -c "\i data-seeds.sql" hips
elif [[ "$OSTYPE" == "darwin"* ]]; then
    psql -c "\i data-seeds.sql" hips
fi