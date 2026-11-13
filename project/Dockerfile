FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY . .
RUN chmod +x gradlew && apt-get update && apt-get install -y --no-install-recommends libatomic1 && rm -rf /var/lib/apt/lists/*
CMD ["sh", "-c", "ln -sf /app/build/allure/node/bin/node /usr/local/bin/node && ./gradlew --no-daemon test -Dselenoid=true"]
