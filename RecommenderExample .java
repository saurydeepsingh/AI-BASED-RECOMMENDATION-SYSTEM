import org.apache.mahout.cf.taste.eval.*;
import org.apache.mahout.cf.taste.impl.eval.*;
import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;

import java.io.*;
import java.util.List;

public class RecommenderExample {
    public static void main(String[] args) {
        try {
            DataModel model = new FileDataModel(new File("data.csv"));
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);
            Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
            int userId = 2;
            List<RecommendedItem> recommendations = recommender.recommend(userId, 3);
            System.out.println("Recommendations for User " + userId + ":");
            for (RecommendedItem recommendation : recommendations) {
                System.out.printf("Item ID: %d, Estimated Preference: %.2f\n",
                        recommendation.getItemID(), recommendation.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
