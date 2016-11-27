package com.proximate.www.pushmate.wservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.myparkmeter.models.User;
import com.myparkmeter.request.LoginRequest;
import com.myparkmeter.request.SaveUserRequest;
import com.myparkmeter.response.CheckinResponse;
import com.myparkmeter.response.LoginResponse;
import com.myparmeter.dao.IUserDAO;

@Component
@Path("/users_service")
public class UserService {
	
	@Autowired
	IUserDAO userDAO;
	
	@SuppressWarnings({ "finally" })
	@Path("/login.do")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@QueryParam("email") String email, @QueryParam("password") String password, @QueryParam("userId") String userId) {
		LoginResponse br = new LoginResponse();
		Gson gson = new Gson();
		String responseJSON = null;
		try {
			if (email != null && password != null) {
				
				LoginRequest login = new LoginRequest();
				login.setEmail(email);
				login.setPassword(password);
				login.setUserid(userId); 
				
				br.setUser(userDAO.userExists(login));
				
				if (br.getUser() != null && br.getUser().getEmail() != null) {
					br.setSuccess(true);
					br.setMensaje("");
					// Convertir response a json
					responseJSON = gson.toJson(br);				
					return responseJSON;

				} else {
					br.setSuccess(false);
					br.setMensaje("User does not exist.");
					// Convertir response a json
					responseJSON = gson.toJson(br);				
					return responseJSON;					
				}
			} else {
				br.setSuccess(false);
				br.setMensaje("Email and password are obligatory.");
				// Convertir response a json
				responseJSON = gson.toJson(br);	
			}
		} catch (Exception e) {
			e.printStackTrace();
			br.setSuccess(false);
			br.setMensaje("Error.");
			// Convertir response a json
			responseJSON = gson.toJson(br);				
			return responseJSON;
		} finally {
			return responseJSON;
		}
	}
	
	@SuppressWarnings({ "finally" })
	@Path("/registerUser.do")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String registerUser(@QueryParam("email") String email, @QueryParam("password") String password, 
			@QueryParam("name") String name, @QueryParam("plate") String plate, @QueryParam("type") int type) {
		LoginResponse br = new LoginResponse();
		Gson gson = new Gson();
		String responseJSON = null;
		try {	
			switch (type) {
				case 1:
					// Create new user
					SaveUserRequest findUser1 = new SaveUserRequest();
					findUser1.setEmail(email);
					findUser1.setPassword(password);
											
					if (password != null && email != null && name != null) {
						if (userDAO.userExists(findUser1).getEmail() != null) {
							br.setSuccess(false);
							br.setMensaje("The email is already taken.");
							// Convertir response a json
							responseJSON = gson.toJson(br);	
						} else {
							User user = new User();
							user.setEmail(email);
							user.setPassword(password); 
							user.setName(name); 
							userDAO.insert(user); 
							br.setSuccess(true);
							br.setMensaje("The user was registered successfully.");
							// Convertir response a json
							responseJSON = gson.toJson(br);					
						}							
					} else {
						br.setSuccess(false);
						br.setMensaje("The email, the password and the name are obligatory.");
						// Convertir response a json
						responseJSON = gson.toJson(br);
					}
				break;
				case 2:
					// Send email
					br.setSuccess(true);
					br.setMensaje("An email was send so you can change your password.");
					// Convertir response a json
					responseJSON = gson.toJson(br);
				break;
				case 3:
					// Update user
					if (password != null && email != null && name != null && plate != null) {
						SaveUserRequest findUser2 = new SaveUserRequest();
						findUser2.setEmail(email);
						findUser2.setPassword(password);
						User tempUser = userDAO.userExists2(findUser2);
						tempUser.setEmail(email);
						tempUser.setName(name);
						tempUser.setPlate(plate);
						tempUser.setPassword(password); 
						System.out.println("tempUser: " + tempUser.toString());
						userDAO.insert(tempUser); 
						br.setSuccess(true);
						br.setMensaje("The user was updated successfully.");
						// Convertir response a json
						responseJSON = gson.toJson(br);	
					} else {
						br.setSuccess(false);
						br.setMensaje("The email, the password, the plate and the name are obligatory.");
						// Convertir response a json
						responseJSON = gson.toJson(br);
					}
				break;
				default:
					
			}
			return responseJSON;
		} catch (Exception e) {
			e.printStackTrace();
			br.setSuccess(false);
			br.setMensaje("Error.");
			// Convertir response a json
			responseJSON = gson.toJson(br);				
			return responseJSON;
		} finally {
			return responseJSON;
		}
	}
	
	
	@SuppressWarnings({ "finally" })
	@Path("/isChecked.do")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String isChecked(@QueryParam("email") String email) {
		LoginResponse br = new LoginResponse();
		CheckinResponse cr = new CheckinResponse();
		Gson gson = new Gson();
		String responseJSON = null;
		try {
			if (email != null) {
				
				LoginRequest login = new LoginRequest();
				login.setEmail(email);
				
				br.setUser(userDAO.userExists2(login));
				
				if (br.getUser() != null && br.getUser().getEmail() != null) {
					if (br.getUser().getCard() == 1) {
						cr.setSuccess(true);
						cr.setMensaje("");
						// Convertir response a json
						cr.setResult(br.getUser().getChecked());
						cr.setEmail(br.getUser().getEmail());
						responseJSON = gson.toJson(cr);				
						return responseJSON;
					} else {
						cr.setSuccess(false);
						cr.setMensaje("You need to register a payment method.");
						// Convertir response a json
						responseJSON = gson.toJson(cr);				
						return responseJSON;							
					}
				} else {
					cr.setSuccess(false);
					cr.setMensaje("User does not exist.");
					// Convertir response a json
					responseJSON = gson.toJson(cr);				
					return responseJSON;					
				}
			} else {
				cr.setSuccess(false);
				cr.setMensaje("Email is obligatory.");
				// Convertir response a json
				responseJSON = gson.toJson(cr);	
			}
		} catch (Exception e) {
			e.printStackTrace();
			cr.setSuccess(false);
			cr.setMensaje("Error.");
			// Convertir response a json
			responseJSON = gson.toJson(cr);				
			return responseJSON;
		} finally {
			return responseJSON;
		}
	}
	
	@SuppressWarnings({ "finally" })
	@Path("/checkInOut1.do")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String checkInOut1(@QueryParam("email") String email, @QueryParam("type") int type) {
		LoginResponse br = new LoginResponse();
		CheckinResponse cr = new CheckinResponse();
		Gson gson = new Gson();
		String responseJSON = null;
		try {
			if (email != null && type != 0) {
				LoginRequest login = new LoginRequest();
				login.setEmail(email);
				
				br.setUser(userDAO.userExists2(login));
				
				if (type == 1) { // CheckIn 1st step
					if (br.getUser() != null && br.getUser().getEmail() != null) {
						if (br.getUser().getCard() == 1) {
							cr.setSuccess(true);
							cr.setMensaje("");
							// Convertir response a json
							cr.setResult(br.getUser().getChecked());
							cr.setEmail(br.getUser().getEmail());
							responseJSON = gson.toJson(cr);				
							return responseJSON;
						} else {
							cr.setSuccess(false);
							cr.setMensaje("You need to register a payment method.");
							// Convertir response a json
							responseJSON = gson.toJson(cr);				
							return responseJSON;								
						}
					} else {
						cr.setSuccess(false);
						cr.setMensaje("User does not exist.");
						// Convertir response a json
						responseJSON = gson.toJson(cr);				
						return responseJSON;					
					}
				} else if (type == 2) { // Checkout
					if (br.getUser() != null && br.getUser().getEmail() != null) {
						// Checkout user
						User user = new User();
						user.setEmail(email);
						cr.setSuccess(userDAO.checkoutUser(user));
						cr.setMensaje("");
						// Convertir response a json
						cr.setResult(br.getUser().getChecked());
						cr.setEmail(br.getUser().getEmail());
						responseJSON = gson.toJson(cr);	
						return responseJSON;
					} else {
						cr.setSuccess(false);
						cr.setMensaje("User does not exist.");
						// Convertir response a json
						responseJSON = gson.toJson(cr);				
						return responseJSON;					
					}	
				}
			} else {
				cr.setSuccess(false);
				cr.setMensaje("Email is obligatory.");
				// Convertir response a json
				responseJSON = gson.toJson(cr);	
			}
		} catch (Exception e) {
			e.printStackTrace();
			cr.setSuccess(false);
			cr.setMensaje("Error.");
			// Convertir response a json
			responseJSON = gson.toJson(cr);				
			return responseJSON;
		} finally {
			return responseJSON;
		}
	}
	
	@SuppressWarnings({ "finally" })
	@Path("/checkIn2.do")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String checkIn2(@QueryParam("email") String email, @QueryParam("coordinates") String coordinates, 
			@QueryParam("minutes") int minutes, @QueryParam("hours") int hours) {
		CheckinResponse cr = new CheckinResponse();
		Gson gson = new Gson();
		String responseJSON = null;
		try {
			if (email != null && coordinates != null) {
				
				LoginRequest login = new LoginRequest();
				login.setEmail(email);
				
				LoginResponse br = new LoginResponse();
				br.setUser(userDAO.userExists2(login));
				if (br.getUser().getCard() == 1) {
					User user = new User();
					user.setEmail(email);
					user.setMinutes(minutes);
					user.setHours(hours);
					user.setCoordinates(coordinates); 
					
					userDAO.checkinUser(user);
					
					cr.setSuccess(true);
					cr.setMensaje("");
					// Convertir response a json
					cr.setResult(1);
					cr.setEmail(email);
					responseJSON = gson.toJson(cr);				
					return responseJSON;
				} else {
					cr.setSuccess(false);
					cr.setMensaje("You need to register a payment method.");
					// Convertir response a json
					responseJSON = gson.toJson(cr);				
					return responseJSON;								
				}
			} else {
				cr.setSuccess(false);
				cr.setMensaje("Email and coordinates are obligatory.");
				// Convertir response a json
				responseJSON = gson.toJson(cr);	
			}
		} catch (Exception e) {
			e.printStackTrace();
			cr.setSuccess(false);
			cr.setMensaje("Error.");
			// Convertir response a json
			responseJSON = gson.toJson(cr);				
			return responseJSON;
		} finally {
			return responseJSON;
		}
	}
	
	@SuppressWarnings({ "finally" })
	@Path("/saveCard.do")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String saveCard(@QueryParam("email") String email) {
		CheckinResponse cr = new CheckinResponse();
		Gson gson = new Gson();
		String responseJSON = null;
		try {
			if (email != null) {
				
				LoginRequest login = new LoginRequest();
				login.setEmail(email);
				
				LoginResponse br = new LoginResponse();
				br.setUser(userDAO.userExists2(login));
				if (br.getUser().getEmail() != null) {
					User user = new User();
					user.setEmail(email);
					cr.setSuccess(userDAO.saveCard(user));
					cr.setMensaje("");
					// Convertir response a json
					cr.setResult(1);
					cr.setEmail(email);
					responseJSON = gson.toJson(cr);				
					return responseJSON;
				} else {
					cr.setSuccess(false);
					cr.setMensaje("Email does not exist.");
					// Convertir response a json
					responseJSON = gson.toJson(cr);				
					return responseJSON;								
				}
			} else {
				cr.setSuccess(false);
				cr.setMensaje("Email is obligatory.");
				// Convertir response a json
				responseJSON = gson.toJson(cr);	
			}
		} catch (Exception e) {
			e.printStackTrace();
			cr.setSuccess(false);
			cr.setMensaje("Error.");
			// Convertir response a json
			responseJSON = gson.toJson(cr);				
			return responseJSON;
		} finally {
			return responseJSON;
		}
	}
	
	@SuppressWarnings({ "finally" })
	@Path("/updateUserId.do")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String updateUserId(@QueryParam("email") String email, @QueryParam("userId") String userId) {
		LoginResponse br = new LoginResponse();
		Gson gson = new Gson();
		String responseJSON = null;
		try {
			if (email != null && userId != null) {
				
				LoginRequest login = new LoginRequest();
				login.setEmail(email);
				login.setUserid(userId); 
				
				userDAO.updateUserId(login);
				br.setSuccess(true);
				br.setMensaje("");
				// Convertir response a json
				responseJSON = gson.toJson(br);				
				return responseJSON;				
			} else {
				br.setSuccess(false);
				br.setMensaje("Email and userId are obligatory.");
				// Convertir response a json
				responseJSON = gson.toJson(br);	
			}
		} catch (Exception e) {
			e.printStackTrace();
			br.setSuccess(false);
			br.setMensaje("Error.");
			// Convertir response a json
			responseJSON = gson.toJson(br);				
			return responseJSON;
		} finally {
			return responseJSON;
		}
	}
	
}