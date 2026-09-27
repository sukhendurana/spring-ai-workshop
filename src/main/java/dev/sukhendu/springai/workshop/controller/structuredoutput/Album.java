package dev.sukhendu.springai.workshop.controller.structuredoutput;

import java.util.List;

public record Album(List<Track> tracks) {
}
