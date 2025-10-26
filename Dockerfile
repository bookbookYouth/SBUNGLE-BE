# ============================
# Stage 1: Build the application
# ============================
FROM gradle:8.7-jdk17 AS builder

# 작업 디렉토리 설정
WORKDIR /app

# Gradle 설정 파일과 소스 복사
COPY build.gradle settings.gradle gradlew ./
COPY gradle gradle
COPY src src

# Gradle 캐시 사용을 위해 build 한번 수행
RUN ./gradlew clean build -x test

# ============================
# Stage 2: Run the application
# ============================
FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# (중요) cgroup 리소스 감지 비활성화
ENV JAVA_TOOL_OPTIONS="-XX:-UseContainerSupport"

# (중요) Actuator의 시스템/메트릭 오토컨피그 전부 제외
ENV SPRING_AUTOCONFIGURE_EXCLUDE="org.springframework.boot.actuate.autoconfigure.metrics.SystemMetricsAutoConfiguration,org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration,org.springframework.boot.actuate.autoconfigure.observation.ObservationAutoConfiguration"

# (중요) ProcessorMetrics 바인더 비활성화
ENV MANAGEMENT_METRICS_BINDERS_PROCESSOR_ENABLED="false"

# builder 단계에서 빌드된 jar 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 컨테이너 실행 시 jar 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
