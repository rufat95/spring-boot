package com.ecommerce.Ecommerce.mappers;

import com.ecommerce.Ecommerce.entities.User;
import com.ecommerce.Ecommerce.requests.UserCreateRequest;
import com.ecommerce.Ecommerce.resposes.UserCreateResponse;
import com.ecommerce.Ecommerce.resposes.UserImageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMappers {
    User changeRequest(UserCreateRequest userCreateRequest);
    UserCreateResponse changeUser(User user);
    UserImageResponse changeUserImageResponse(User user);
    List<UserImageResponse> allUsers(List<User> users);
}
