package az.candyshop.CandyShop.mappers;

import az.candyshop.CandyShop.entities.User;
import az.candyshop.CandyShop.requests.UserRequests.UserCreateRequest;
import az.candyshop.CandyShop.responses.UserResponses.UserCreateResponse;
import az.candyshop.CandyShop.responses.UserResponses.UserImageResponse;
import az.candyshop.CandyShop.responses.UserResponses.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMappers {
    // User mappers
    @Mapping(target = "password", ignore = true)
    User changeUserCreateRequestToUser(UserCreateRequest userCreateRequest);
    UserCreateResponse changeUserToUserCreateResponse(User user);
    UserImageResponse changeUserToUserImageResponse(User user);
    UserResponse changeUserToUserResponse(User user);
    List<UserResponse> changePageUserToUserResponse(Page<User> users);
    List<UserResponse> changeListUserToListUserResponse(List<User> users);
}
