package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitHubTests {
    @Test
    public void testCommit()throws IOException {
            Github github = new RtGithub("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"); //соединение с гитхабом через удаленный программный интерфейс
            RepoCommits commits = github.repos().get(new Coordinates.Simple("Anastasia0588", "Java_for_QA")).commits();
            for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
                System.out.println(new RepoCommit.Smart(commit).message());
            }
    }
}

