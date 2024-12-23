package my.com.maybank.assessment;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.shaded.org.awaitility.Durations;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    MSSQLServerContainer<?> sqlServerContainer() {
        return new MSSQLServerContainer<>(
                DockerImageName.parse("mcr.microsoft.com/mssql/server:2022-latest")
                        .asCompatibleSubstituteFor("mcr.microsoft.com/mssql/server"))
                .withPassword("YourStrong@Passw0rd");
    }

}
