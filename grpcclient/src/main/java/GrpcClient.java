


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @author Sourabh Sharma
 */
public class GrpcClient {

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        EmployeeServiceGrpc.EmployeeServiceBlockingStub stub =
                EmployeeServiceGrpc.newBlockingStub(channel);

        Response createResponse = stub.create(
                Employee.newBuilder().setFirstName("Mark").setLastName("Butcher")
                        .setDeptId(1L).setSalary(200000)
                        .setAddress(Employee.Address.newBuilder().setHouseNo("406").setStreet("Hill View")
                                .setCity("San Jose")
                                .setState("State")
                                .setCountry("US").build())
                        .build());
        System.out.println("Create Response: \n" + createResponse);

        Response updateResponse = stub.update(
                Employee.newBuilder().setId(2).setFirstName("Mark").setLastName("Butcher II")
                        .setDeptId(1L).setSalary(200000)
                        .setAddress(Employee.Address.newBuilder().setHouseNo("406").setStreet("Mountain View")
                                .setCity("San Jose")
                                .setState("State")
                                .setCountry("US").build())
                        .build());
        System.out.println("Update Response: \n" + updateResponse);

        EmployeeList employeeList = stub.list(Empty.newBuilder().build());
        System.out.println("Employees: \n" + employeeList);

        Response deleteResponse = stub.delete(EmployeeId.newBuilder().setId(2).build());
        System.out.println("Delete Response: \n" + deleteResponse);

        channel.shutdown();
    }
}