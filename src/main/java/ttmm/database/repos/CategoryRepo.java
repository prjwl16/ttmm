package ttmm.database.repos;

import ttmm.database.SqlFinder;
import ttmm.database.models.Category;

public enum CategoryRepo {
    INSTANCE;
    public final SqlFinder<Long, Category> categoryFinder = new SqlFinder<>(Category.class);

}
