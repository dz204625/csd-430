package servlet;

import dao.MovieDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Movie;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/MovieServlet/*")
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovieDAO dao = new MovieDAO();

    @Override
    public void init() throws ServletException {
        super.init();
        // ensure table exists on startup
        dao.resetTable();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    private void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) action = "list";
        
        switch (action) {
            case "reset":
                dao.resetTable();
                req.setAttribute("message", "Table reset.");
                list(req, resp);
                break;
            case "populate":
                dao.populateTable();
                req.setAttribute("message", "Sample data inserted.");
                list(req, resp);
                break;
            case "createForm":
                req.getRequestDispatcher("/create.jsp").forward(req, resp);
                break;
            case "insert":
                processCreate(req, resp);
                break;
            case "edit":
                processEditForm(req, resp);
                break;
            case "update":
                processUpdate(req, resp);
                break;
            case "delete":
                processDelete(req, resp);
                break;
            default: // list
                list(req, resp);
                break;
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Movie> movies = dao.getAllMovies();
        req.setAttribute("movies", movies);
        req.getRequestDispatcher("/movies.jsp").forward(req, resp);
    }

    private void processCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String title = req.getParameter("title");
        Movie m = new Movie();
        m.setTitle(title);
        m.setDirector(req.getParameter("director"));
        String ry = req.getParameter("releaseYear");
        if (ry != null && !ry.isEmpty()) m.setReleaseYear(Integer.parseInt(ry));
        m.setGenre(req.getParameter("genre"));
        String r = req.getParameter("rating");
        if (r != null && !r.isEmpty()) m.setRating(Double.parseDouble(r));

        dao.createMovie(m);
        resp.sendRedirect(req.getContextPath() + "/MovieServlet?action=list");
    }

    private void processEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Movie m = dao.getMovieById(Integer.parseInt(id));
        req.setAttribute("movie", m);
        req.getRequestDispatcher("/editMovie.jsp").forward(req, resp);
    }

    private void processUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Movie m = new Movie();
        m.setMovieId(Integer.parseInt(req.getParameter("movieId")));
        m.setTitle(req.getParameter("title"));
        m.setDirector(req.getParameter("director"));
        String ry = req.getParameter("releaseYear");
        if (ry != null && !ry.isEmpty()) m.setReleaseYear(Integer.parseInt(ry));
        m.setGenre(req.getParameter("genre"));
        String r = req.getParameter("rating");
        if (r != null && !r.isEmpty()) m.setRating(Double.parseDouble(r));
        dao.updateMovie(m);
        resp.sendRedirect(req.getContextPath() + "/MovieServlet?action=list");
    }

    private void processDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if (id != null && !id.isEmpty()) {
            dao.deleteMovie(Integer.parseInt(id));
        }
        resp.sendRedirect(req.getContextPath() + "/MovieServlet?action=list");
    }
}
