<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>GadgetShop</title>

        <!-- Custom fonts for this template -->
        <link href="recordProduct.css" rel="stylesheet" type="text/css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap4.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="recordCustomer.jsp">

                    <div class="sidebar-brand-text mx-3">GadgetShop Staff</div>
                </a>

                <!-- Divider -->
                <hr class="sidebar-divider my-0">

                <!-- Divider -->
                <hr class="sidebar-divider">

                <!-- Nav Item - Tables -->
                <li class="nav-item active">
                    <a class="nav-link" href="recordCustomer.jsp">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Customer</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="recordProduct.jsp">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Product</span></a>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">

            </ul>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                        <!-- Sidebar Toggle (Topbar) -->
                        <form class="form-inline">
                            <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                                <i class="fa fa-bars"></i>
                            </button>
                        </form>

                        <h3>GADGETSHOP</h3>
                        <!-- Topbar Navbar -->
                        <ul class="navbar-nav ml-auto">
                            <!-- Nav Item - Search Dropdown -->
                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-search fa-fw"></i>
                                </a>
                                <!-- Dropdown - Messages -->
                                <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                     aria-labelledby="searchDropdown">
                                    <form class="form-inline mr-auto w-100 navbar-search">
                                        <div class="input-group">
                                            <input type="text" class="form-control bg-light border-0 small"
                                                   placeholder="Search for..." aria-label="Search"
                                                   aria-describedby="basic-addon2">
                                            <div class="input-group-append">
                                                <button class="btn btn-primary" type="button">
                                                    <i class="fas fa-search fa-sm"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </li>

                            <!-- Nav Item - User Information -->
                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">Username</span>
                                </a>
                                <!-- Dropdown - User Information -->
                                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                     aria-labelledby="userDropdown">
                                    <a class="dropdown-item" href="#">
                                        <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Profile
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Logout
                                    </a>
                                </div>
                            </li>

                        </ul>
                    </nav>

                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <h1 class="h3 mb-2 text-gray-800">Product Records</h1>

                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Product ID</th>
                                                <th>Name</th>
                                                <th>Category</th>
                                                <th>Price</th>
                                                <th>Description</th>
                                                <th>Image</th>
                                                <th></th>
                                            </tr>
                                        </thead>

                                        <tr>
                                        <form action="/recordProduct" method="POST"/>
                                        <input type="hidden" name="action" value="addProd">

                                        <td><input style="width: 100px;" type="text" placeholder="Product ID" name="id" value="AUTO" readonly><br></td>

                                        <td><input type="text" placeholder="Name" name="name"><br>
                                            <% if (request.getAttribute("nameError") != null) {%>
                                            <span style="color: red;"><%= request.getAttribute("nameError")%></span>
                                            <% request.removeAttribute("nameError"); %>
                                            <% } %>
                                        </td>

                                        <td>
                                            <select name="category">
                                                <%Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/GadgetShop", "nbuser", "nbuser");
                                                    Statement st = con.createStatement();
                                                    ResultSet rs;
                                                    rs = st.executeQuery("SELECT * FROM CATEGORIES");
                                                    while (rs.next()) {%>
                                                <option value="<%= rs.getString(1)%>"><%= rs.getString(2)%></option><%  }%>
                                            </select>
                                        </td>

                                        <td><input type="text" placeholder="Price" name="price"><br>
                                            <% if (request.getAttribute("priceError") != null) {%>
                                            <span style="color: red;"><%= request.getAttribute("priceError")%></span>
                                            <% request.removeAttribute("priceError"); %>
                                            <% } %>
                                        </td>

                                        <td><input type="text" placeholder="Description" name="desc"><br>
                                            <% if (request.getAttribute("descError") != null) {%>
                                            <span style="color: red;"><%= request.getAttribute("descError")%></span>
                                            <% request.removeAttribute("descError"); %>
                                            <% } %>
                                        </td>


                                        <td><input type="file" name="image" accept=".jpg, .jpeg, .png" required></td>

                                        <td><b><button class="primary" type="submit">Add</button></b></td>
                                        </tr>

                                        <%   String query = "SELECT P.*, PI.IMAGE FROM PRODUCTS P LEFT JOIN PRODUCT_IMAGES PI ON P.PRODUCT_ID = PI.PRODUCT_ID";
                                            rs = st.executeQuery(query);
                                            while (rs.next()) {

                                                String id = rs.getString("PRODUCT_ID");
                                                String name = rs.getString("PRODUCT_NAME");
                                                String category = rs.getString("CATEGORY_ID");
                                                String price = rs.getString("PRICE");
                                                String desc = rs.getString("DESCRIPTION");
                                                String img = rs.getString("IMAGE");

                                                out.println("<input type='hidden' name='action' value='update'>");
                                                out.println("<input type='hidden' name='oldId' value='" + id + "'>");
                                                out.println("<tr>");
                                                out.println("<td>" + id + "</td>");
                                                out.println("<td>" + name + "</td>");
                                                out.println("<td>" + category + "</td>");
                                                out.println("<td>" + price + "</td>");
                                                out.println("<td>" + desc + "</td>");
                                                out.println("<td>" + img + "</td>");
                                                out.println("<td>");
                                                out.println("<select class='primary' onchange='handleAction(\"" + id + "\", \"" + name + "\", \"" + category + "\", \"" + price + "\", \"" + desc + "\", \"" + img + "\", this)'>");
                                                out.println("<form action='recordProduct.java' method='POST'/>");
                                                out.println("<option class='opt' value='update'>Update</option>");
                                                out.println("<option class='opt' value='delete'>Delete</option>");
                                                out.println("</select>");
                                                out.println("</td>");
                                                out.println("</tr>");

                                            }
                                        %>

                                        <% if (request.getAttribute("updateError") != null) {%>
                                        <script>
                                            alert("<%= request.getAttribute("updateError")%>");
                                        </script>
                                        <% } %>        
                                        <dialog id="dialog">
                                            <form action='/recordProduct' method='POST'/>
                                            <input type="hidden" name="action" value="updateProd">
                                            <input type="hidden" name="oldId" id="oldId">
                                            <p>Product ID<br/><input type="text" name="id" id="editId" placeholder="Enter New Id" readonly/></p>
                                            <p>Name<br/><input type="text" name="name" id="editName" placeholder="Enter New Name"/></p>
                                            <p>Category<br/><select name="category" id="editCategory">
                                                    <%
                                                        rs = st.executeQuery("SELECT * FROM CATEGORIES");
                                                        while (rs.next()) {%>
                                                    <option value="<%= rs.getString(1)%>"><%= rs.getString(2)%></option>
                                                    <%  }
                                                        st.close();%>
                                                </select></p>
                                            <p>Price<br/><input type="text" name="price" id="editPrice" placeholder="Enter New Price"/></p>
                                            <p>Description<br/><input type="text" name="desc" id="editDesc" placeholder="Enter New Description"/></p>

                                            <p>Image<br/><input type="file" name="editImage" id="editImage" accept=".jpg, .jpeg, .png"></p>
                                            <input type="submit" value="Update">
                                            <button onclick="window.dialog.close();" aria-label="close" class="x">❌</button>
                                        </dialog>

                                        <% Boolean deleteSuccess = (Boolean) request.getAttribute("deleteSuccess"); %>
                                        <% if (deleteSuccess != null) {%>

                                        <script>
                                            var success = <%= deleteSuccess%>;
                                            if (success) {
                                                alert("Product record successfully deleted!");
                                            } else {
                                                alert("No product record found with the given ID. Deletion failed.");
                                            }
                                        </script>
                                        <% }%>

                                    </table>

                                    <script>
                                        function handleAction(id, name, category, price, desc, img, select) {
                                            var selectedOption = select.value;
                                            if (selectedOption === 'update') {
                                                showDialog(id, name, category, price, desc, img);
                                            } else if (selectedOption === 'delete') {
                                                showDeleteConfirmation(id, name, category, price, desc, img);
                                            }
                                        }

                                        function showDialog(id, name, category, price, desc, img) {
                                            document.getElementById("oldId").value = id;
                                            document.getElementById("editId").value = id;
                                            document.getElementById("editName").value = name;
                                            document.getElementById("editCategory").value = category;
                                            document.getElementById("editPrice").value = price;
                                            document.getElementById("editDesc").value = desc;
                                            window.dialog.showModal();

                                        }

                                        function showDeleteConfirmation(id, name, category, price, desc, img) {
                                            document.getElementById("showId").textContent = id;
                                            document.getElementById("showName").textContent = name;
                                            document.getElementById("showCategory").textContent = category;
                                            document.getElementById("showPrice").textContent = price;
                                            document.getElementById("showDesc").textContent = desc;
                                            document.getElementById("showImage").src = img;
                                            window.dialog.showModal();
                                        }

                                    </script>
                                </div>
                            </div>
                        </div>

                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright &copy; GadgetShop</span>
                        </div>
                    </div>
                </footer>
                <!-- End of Footer -->

            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->

        <!--         Scroll to Top Button
                <a class="scroll-to-top rounded" href="#page-top">
                    <i class="fas fa-angle-up"></i>
                </a>
        
                <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">×</span>
                                </button>
                            </div>
                            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                            <div class="modal-footer">
                                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                                <a class="btn btn-primary" href="AdminLoginPage.jsp">Logout</a>
                            </div>
                        </div>
                    </div>
                </div>-->

        <!--        <script src="recordProduct.js"></script>-->
        <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap4.min.js"></script>

    </body>

</html>
