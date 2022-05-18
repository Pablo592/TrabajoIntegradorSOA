package ar.edu.iua.iw3.web.RestController;

import ar.edu.iua.iw3.negocio.IUsuarioNegocio;
import ar.edu.iua.iw3.modelo.persistencia.Usuario;
import ar.edu.iua.iw3.negocio.excepciones.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;



@RequestMapping("/auth")
@RestController
public class CoreRestController extends BaseRestController{
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUsuarioNegocio userBusiness;

	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@PostMapping(value="/login")
	public ResponseEntity<String> loginToken(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {
		try {
			Usuario u = userBusiness.cargarPorNombreOEmail(username);
			String msg = u.checkAccount(passwordEncoder, password);
			if (msg != null) {
				u.agregaIntentoFallido();
				try {
					userBusiness.modificar(u);
				} catch (BadRequest e) {
					log.error(e.getMessage(), e);
					return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
				} catch (ConflictException e) {
					e.printStackTrace();
					return new ResponseEntity<String>(HttpStatus.CONFLICT);
				}
				return new ResponseEntity<String>(msg, HttpStatus.UNAUTHORIZED);
			} else {
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(u, null,
						u.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
				return new ResponseEntity<String>(userToJson(getUserLogged()).get("authtoken").toString(),
						HttpStatus.OK);
			}
		} catch (NegocioException e) {
			log.error(e.getMessage());
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NoEncontradoException e) {
			return new ResponseEntity<String>("BAD_ACCOUNT_NAME", HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(value="/login-json")
	public ResponseEntity<String> loginTokenFullJson(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {
		try {
			Usuario u = userBusiness.cargarPorNombreOEmail(username);
			String msg = u.checkAccount(passwordEncoder, password);
			if (msg != null) {
				u.agregaIntentoFallido();
				try {
					userBusiness.modificar(u);
				} catch (BadRequest e) {
					log.error(e.getMessage(), e);
					return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
				} catch (ConflictException e) {
					e.printStackTrace();
					return new ResponseEntity<String>(HttpStatus.CONFLICT);
				}
				return new ResponseEntity<String>(msg, HttpStatus.UNAUTHORIZED);
			} else {
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(u, null,
						u.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
				return new ResponseEntity<String>(userToJson(getUserLogged()).toString(),
						HttpStatus.OK);
			}
		} catch (NegocioException e) {
			log.error(e.getMessage());
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NoEncontradoException e) {
			return new ResponseEntity<String>("BAD_ACCOUNT_NAME", HttpStatus.UNAUTHORIZED);
		}

	}

	@GetMapping(value = "/auth-info")	//si no estoy logueado me devuelve un 403 y sino me devuelve el usuario logueado
	public ResponseEntity<String> authInfo() {
		return new ResponseEntity<String>(userToJson(getUserLogged()).toString(), HttpStatus.OK);
	}

}
